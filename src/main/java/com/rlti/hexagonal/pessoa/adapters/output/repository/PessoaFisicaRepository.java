package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.output.repository.entity.PessoaFisicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaEntity, Long> {
    Optional<PessoaFisicaEntity> findByCpf(String cpf);
}
