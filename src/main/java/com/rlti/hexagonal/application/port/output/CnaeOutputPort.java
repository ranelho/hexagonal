package com.rlti.hexagonal.application.port.output;

import com.rlti.hexagonal.application.core.domain.Cnae;

import java.util.List;

public interface CnaeOutputPort {
    List<Cnae> findAll();

    void addCnaes(Long idPessoa, List<Cnae> cnaes);
}
