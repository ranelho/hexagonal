package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.output.repository.entity.PessoaFisicaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.repository.entity.TelefoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelefoneRepository extends JpaRepository<TelefoneEntity, Long>{
    List<TelefoneEntity> findAllByPessoaFisica(PessoaFisicaEntity pessoaFisicaEntity);

}
