package com.rlti.hexagonal.pessoa.application.port.output;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.Profissao;

public interface ProfissaoOutputPort {

    void processarProfissao(Profissao profissao);
}
