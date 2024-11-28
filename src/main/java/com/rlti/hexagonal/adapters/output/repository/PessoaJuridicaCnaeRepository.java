package com.rlti.hexagonal.adapters.output.repository;

import com.rlti.hexagonal.adapters.output.repository.entity.PessoaJuridicaCnaeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaJuridicaCnaeRepository extends JpaRepository<PessoaJuridicaCnaeEntity, Long> {
}
