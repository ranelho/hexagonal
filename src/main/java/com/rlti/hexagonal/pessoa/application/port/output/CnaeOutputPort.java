package com.rlti.hexagonal.pessoa.application.port.output;

import com.rlti.hexagonal.pessoa.adapters.input.request.CnaeFilter;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.Cnae;

import java.util.List;

public interface CnaeOutputPort {
    List<Cnae> findAll();

    void addCnaes(Long idPessoa, List<Cnae> cnaes);

    void processarCnae(Cnae cnae);

    void salvarSubclasses();

    void addCnaesV2(Long idPessoa, String cnaePrincipal, List<Cnae> cnaes);

    List<Cnae> getAllCnaesWithFilter(CnaeFilter params);
}
