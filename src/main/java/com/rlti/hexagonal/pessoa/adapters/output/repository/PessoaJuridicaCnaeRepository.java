package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica.PessoaJuridicaCnaeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaJuridicaCnaeRepository extends JpaRepository<PessoaJuridicaCnaeEntity, Long> {
}
