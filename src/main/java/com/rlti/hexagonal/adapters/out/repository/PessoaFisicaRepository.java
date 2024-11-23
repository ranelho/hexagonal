package com.rlti.hexagonal.adapters.out.repository;

import com.rlti.hexagonal.adapters.out.repository.entity.PessoaFisicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaEntity, Long> {
}
