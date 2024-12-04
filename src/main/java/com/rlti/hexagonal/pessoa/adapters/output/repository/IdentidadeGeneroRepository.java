package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.output.repository.entity.IdentidadeGeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentidadeGeneroRepository extends JpaRepository<IdentidadeGeneroEntity, Long> {
}
