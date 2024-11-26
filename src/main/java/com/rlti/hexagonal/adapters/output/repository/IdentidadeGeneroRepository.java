package com.rlti.hexagonal.adapters.output.repository;

import com.rlti.hexagonal.adapters.output.repository.entity.IdentidadeGeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentidadeGeneroRepository extends JpaRepository<IdentidadeGeneroEntity, Long> {
}
