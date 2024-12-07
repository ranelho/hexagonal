package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.output.entity.banco.AgenciaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.banco.BancoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenciaRepository extends JpaRepository<AgenciaEntity, Long> {
    Page<AgenciaEntity> findAllByBanco(BancoEntity bancoEntity, Pageable pageable);
}
