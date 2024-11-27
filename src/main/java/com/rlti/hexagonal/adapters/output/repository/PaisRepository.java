package com.rlti.hexagonal.adapters.output.repository;

import com.rlti.hexagonal.adapters.output.repository.entity.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<PaisEntity, Long> {
}
