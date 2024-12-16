package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.ProfissaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissaoRepository extends JpaRepository<ProfissaoEntity, Long>, ProfissaoRepositoryCustom {
    boolean existsByCodigo(Long codigo);
}
