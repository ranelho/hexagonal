package com.rlti.hexagonal.adapters.out.repository;

import com.rlti.hexagonal.adapters.out.repository.entity.PessoaJuridicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridicaEntity, Long> {
    Optional<PessoaJuridicaEntity> findByCnpj(String cnpj);
}
