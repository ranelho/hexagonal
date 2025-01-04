package com.rlti.hexagonal.pessoa.adapters.output.service;

import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.GrauInstrucaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrauInstrucaoRepository extends JpaRepository<GrauInstrucaoEntity, Long> {
}
