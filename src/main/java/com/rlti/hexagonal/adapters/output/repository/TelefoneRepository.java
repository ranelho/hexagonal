package com.rlti.hexagonal.adapters.output.repository;

import com.rlti.hexagonal.adapters.output.repository.entity.PessoaEntity;
import com.rlti.hexagonal.adapters.output.repository.entity.PessoaFisicaEntity;
import com.rlti.hexagonal.adapters.output.repository.entity.TelefoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelefoneRepository extends JpaRepository<TelefoneEntity, Long>{
    List<TelefoneEntity> findAllByPessoa(PessoaEntity pessoaEntity);

}
