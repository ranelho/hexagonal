package com.rlti.hexagonal.pessoa.application.port.output;

import com.rlti.hexagonal.pessoa.application.core.domain.Cnae;

import java.util.List;

public interface CnaeOutputPort {
    List<Cnae> findAll();

    void addCnaes(Long idPessoa, List<Cnae> cnaes);

    void processarCnae(Cnae cnae);

    void salvarSubclasses();
}
