package com.rlti.hexagonal.pessoa.application.port.output;

import com.rlti.hexagonal.pessoa.adapters.input.request.ProfissaoFilter;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.Profissao;

import java.util.List;

public interface ProfissaoOutputPort {

    void processarProfissao(Profissao profissao);

    List<Profissao> getAllProfissoesWithFilter(ProfissaoFilter params);
}
