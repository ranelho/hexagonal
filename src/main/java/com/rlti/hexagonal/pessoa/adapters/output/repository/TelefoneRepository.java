package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.PessoaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.TelefoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelefoneRepository extends JpaRepository<TelefoneEntity, Long>{
    List<TelefoneEntity> findAllByPessoa(PessoaEntity pessoa);

}
