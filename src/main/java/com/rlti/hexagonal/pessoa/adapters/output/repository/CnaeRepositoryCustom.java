package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.input.request.CnaeFilter;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica.CnaeEntity;

import java.util.List;

public interface CnaeRepositoryCustom {
    List<CnaeEntity> getWithFilter(CnaeFilter params);
}
