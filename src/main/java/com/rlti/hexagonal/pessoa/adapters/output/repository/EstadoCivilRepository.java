package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.EstadoCivilEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoCivilRepository extends JpaRepository<EstadoCivilEntity, Long> {
}
