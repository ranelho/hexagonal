package com.rlti.hexagonal.adapters.out.repository;

import com.rlti.hexagonal.adapters.out.repository.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {
}
