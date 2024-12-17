package com.rlti.hexagonal.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.BiFunction;

public class CriteriaQueryHelper {

    private final EntityManager entityManager;

    public CriteriaQueryHelper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Método genérico para busca paginada com filtros.
     *
     * @param entityClass Classe da entidade principal.
     * @param rootJoinFunction Função para criar os joins e os Predicates.
     * @param pageable Objeto de paginação.
     * @return Página com os resultados.
     * @param <T> Tipo da entidade principal.
     * @param <R> Tipo do DTO ou modelo de domínio.
     */
    public <T, R> Page<R> findWithFilters(
            Class<T> entityClass,
            BiFunction<CriteriaBuilder, Root<T>, List<Predicate>> rootJoinFunction,
            Pageable pageable,
            Class<R> dtoClass,
            ModelMapper modelMapper
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // Consulta principal
        CriteriaQuery<T> query = cb.createQuery(entityClass);
        Root<T> root = query.from(entityClass);

        List<Predicate> predicates = rootJoinFunction.apply(cb, root);
        query.where(predicates.toArray(Predicate[]::new));

        // Paginação e execução da consulta principal
        TypedQuery<T> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
        List<T> content = typedQuery.getResultList();

        // Consulta de contagem
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<T> countRoot = countQuery.from(entityClass);
        List<Predicate> countPredicates = rootJoinFunction.apply(cb, countRoot);
        countQuery.select(cb.count(countRoot));
        countQuery.where(countPredicates.toArray(Predicate[]::new));
        long total = entityManager.createQuery(countQuery).getSingleResult();

        // Conversão para DTO ou modelo de domínio
        List<R> results = content.stream()
                .map(entity -> modelMapper.map(entity, dtoClass))
                .toList();

        return new PageImpl<>(results, pageable, total);
    }
}
