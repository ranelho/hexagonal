package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.output.repository.entity.CnaeEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CnaeRepository extends JpaRepository<CnaeEntity, Long> {
    List<CnaeEntity> findBySubclasseIn(@Param("subclasse") List<String> subclasse);

    Optional<CnaeEntity> findBySubclasse(String subclasse);

    boolean existsBySubclasse(String subclasse);
}
