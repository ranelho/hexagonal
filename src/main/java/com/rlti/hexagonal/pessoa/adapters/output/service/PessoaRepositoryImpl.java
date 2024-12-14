package com.rlti.hexagonal.pessoa.adapters.output.service;

import com.rlti.hexagonal.pessoa.adapters.input.request.PessoaFilterParam;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.PessoaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.PessoaFisicaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.repository.PessoaRepositoryCustom;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.PessoaFisica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

@Repository
@Primary
@RequiredArgsConstructor
public class PessoaRepositoryImpl implements PessoaRepositoryCustom {

    private final EntityManager entityManager;
    private final ModelMapper modelMapper;

    @Override
    public Page<PessoaFisica> findWithFilters(PessoaFilterParam params, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // Consulta principal
        CriteriaQuery<PessoaFisicaEntity> query = cb.createQuery(PessoaFisicaEntity.class);
        Root<PessoaFisicaEntity> root = query.from(PessoaFisicaEntity.class);

        // Faz o join com a entidade PessoaEntity
        Join<PessoaFisicaEntity, PessoaEntity> pessoaJoin = root.join("pessoa");

        List<Predicate> predicates = buildPredicates(params, cb, root, pessoaJoin);
        query.where(predicates.toArray(new Predicate[0]));

        // Paginação e execução da consulta principal
        TypedQuery<PessoaFisicaEntity> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
        List<PessoaFisicaEntity> content = typedQuery.getResultList();

        // Consulta de contagem
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<PessoaFisicaEntity> countRoot = countQuery.from(PessoaFisicaEntity.class);
        Join<PessoaFisicaEntity, PessoaEntity> countPessoaJoin = countRoot.join("pessoa");

        List<Predicate> countPredicates = buildPredicates(params, cb, countRoot, countPessoaJoin); // Reutiliza a lógica
        countQuery.select(cb.count(countRoot));
        countQuery.where(countPredicates.toArray(new Predicate[0]));
        long total = entityManager.createQuery(countQuery).getSingleResult();

        // Conversão para DTO ou modelo de domínio
        List<PessoaFisica> pessoas = content.stream()
                .map(entity -> modelMapper.map(entity, PessoaFisica.class))
                .toList();

        return new PageImpl<>(pessoas, pageable, total);
    }

    /**
     * Método auxiliar para construir os predicados dinamicamente.
     */
    private List<Predicate> buildPredicates(PessoaFilterParam params,
                                            CriteriaBuilder cb,
                                            Root<PessoaFisicaEntity> root,
                                            Join<PessoaFisicaEntity, PessoaEntity> pessoaJoin) {
        List<Predicate> predicates = new ArrayList<>();

        // Função auxiliar para adicionar predicados dinamicamente
        BiConsumer<Boolean, Supplier<Predicate>> addPredicateIf = (condition, predicateSupplier) -> {
            if (Boolean.TRUE.equals(condition)) {
                predicates.add(predicateSupplier.get());
            }
        };

        // Adicionando os filtros dinamicamente
        addPredicateIf.accept(params.getNome() != null,
                () -> cb.like(cb.lower(pessoaJoin.get("nome")), "%" + params.getNome().toLowerCase() + "%"));

        addPredicateIf.accept(params.getCpf() != null,
                () -> cb.equal(root.get("cpf"), params.getCpf()));

        addPredicateIf.accept(params.isEstrangeiro(),
                () -> cb.equal(root.get("estrangeiro"), "S"));

        addPredicateIf.accept(params.getSexo() != null,
                () -> cb.equal(root.get("sexo"), params.getSexo()));

        return predicates;
    }

}
