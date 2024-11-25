package com.rlti.hexagonal.adapters.output.repository;

import com.rlti.hexagonal.adapters.output.repository.entity.AgenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenciaRepository extends JpaRepository<AgenciaEntity, Long> {
}
