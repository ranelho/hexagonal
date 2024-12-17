package com.rlti.hexagonal.pessoa.adapters.output.repository.impl;

import com.rlti.hexagonal.pessoa.adapters.input.request.PessoaFilterParam;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.PessoaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.PessoaFisicaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.repository.PessoaRepositoryCustom;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.PessoaFisica;
import com.rlti.hexagonal.util.CriteriaQueryHelper;
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
        CriteriaQueryHelper queryHelper = new CriteriaQueryHelper(entityManager);

        return queryHelper.findWithFilters(
                PessoaFisicaEntity.class,
                (cb, root) -> {
                    // Join com a entidade PessoaEntity
                    Join<PessoaFisicaEntity, PessoaEntity> pessoaJoin = root.join("pessoa");

                    // Criação dos Predicates
                    return buildPredicates(params, cb, root, pessoaJoin);
                },
                pageable,
                PessoaFisica.class,
                modelMapper
        );
    }

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
