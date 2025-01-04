package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.CorRacaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorRacaRepository extends JpaRepository<CorRacaEntity, Long> {
}
