package com.rlti.hexagonal.adapters.output.repository;

import com.rlti.hexagonal.adapters.output.repository.entity.ContaBancariaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaBancariaRepository extends JpaRepository<ContaBancariaEntity, Long> {
}
