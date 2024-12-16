package com.rlti.hexagonal.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CriteriaUtil {
    public static <T> Root<T> createRoot(EntityManager entityManager, Class<T> entityClass) {
        // Obter o CriteriaBuilder do EntityManager
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // Criar uma CriteriaQuery para a classe fornecida
        CriteriaQuery<T> query = cb.createQuery(entityClass);

        // Retornar o Root associado à entidade
        return query.from(entityClass);
    }
}
