package com.rlti.hexagonal.adapters.output.repository;

import com.rlti.hexagonal.adapters.output.repository.entity.OrientacaoSexualEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrientacaoSexualRepository extends JpaRepository<OrientacaoSexualEntity, Long> {
}
