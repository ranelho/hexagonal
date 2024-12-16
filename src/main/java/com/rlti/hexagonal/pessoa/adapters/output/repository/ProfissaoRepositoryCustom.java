package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.input.request.ProfissaoFilter;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.ProfissaoEntity;

import java.util.List;

public interface ProfissaoRepositoryCustom {
    List<ProfissaoEntity> getWithFilter(ProfissaoFilter params);
}
