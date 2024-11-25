package com.rlti.hexagonal.adapters.output.repository;

import com.rlti.hexagonal.adapters.output.repository.entity.AgenciaEntity;
import com.rlti.hexagonal.adapters.output.repository.entity.BancoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenciaRepository extends JpaRepository<AgenciaEntity, Long> {
    Page<AgenciaEntity> findAllByBanco(BancoEntity bancoEntity, Pageable pageable);
}
