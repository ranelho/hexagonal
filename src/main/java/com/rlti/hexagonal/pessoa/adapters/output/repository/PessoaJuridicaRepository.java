package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.output.repository.entity.PessoaJuridicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridicaEntity, Long> {
    Optional<PessoaJuridicaEntity> findByCnpj(String cnpj);
}
