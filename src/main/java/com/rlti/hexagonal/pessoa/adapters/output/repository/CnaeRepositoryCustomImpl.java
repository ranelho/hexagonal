package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.input.request.CnaeFilter;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica.CnaeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CnaeRepositoryCustomImpl implements CnaeRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<CnaeEntity> getWithFilter(CnaeFilter params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CnaeEntity> query = cb.createQuery(CnaeEntity.class);
        Root<CnaeEntity> cnaeRoot = query.from(CnaeEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        if (params.getDenominacao() != null) {
            predicates.add(cb.like(cnaeRoot.get("denominacao"), "%" + params.getDenominacao() + "%"));
        }
        if (params.getSubclasse() != null) {
            predicates.add(cb.like(cnaeRoot.get("subclasse"), "%" + params.getSubclasse() + "%"));
        }
        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(Predicate[]::new));
        }
        TypedQuery<CnaeEntity> queryResult = this.entityManager.createQuery(query);
        return queryResult.getResultList();
    }
}
