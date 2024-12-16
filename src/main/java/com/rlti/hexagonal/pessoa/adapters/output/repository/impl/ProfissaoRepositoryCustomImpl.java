package com.rlti.hexagonal.pessoa.adapters.output.repository.impl;

import com.rlti.hexagonal.pessoa.adapters.input.request.ProfissaoFilter;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.ProfissaoEntity;
import com.rlti.hexagonal.pessoa.adapters.output.repository.ProfissaoRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class ProfissaoRepositoryCustomImpl implements ProfissaoRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<ProfissaoEntity> getWithFilter(ProfissaoFilter params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProfissaoEntity> query = cb.createQuery(ProfissaoEntity.class);
        Root<ProfissaoEntity> cnaeRoot = query.from(ProfissaoEntity.class);

        List<Predicate> predicates = Stream.of(
                        params.getCodigo() != null
                                ? cb.like(cb.toString(cnaeRoot.get("codigo")), "%" + params.getCodigo() + "%")
                                : null,
                        params.getNomeProfissao() != null
                                ? cb.like(cnaeRoot.get("nomeProfissao"), "%" + params.getNomeProfissao() + "%")
                                : null
                ).filter(Objects::nonNull)
                .toList();

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(Predicate[]::new));
        }

        TypedQuery<ProfissaoEntity> queryResult = this.entityManager.createQuery(query);
        return queryResult.getResultList();
    }
}
