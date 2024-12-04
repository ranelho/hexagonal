package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.output.repository.entity.ProfissaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissaoRepository extends JpaRepository<ProfissaoEntity, Long> {
    boolean existsByCodigo(Long codigo);
}
