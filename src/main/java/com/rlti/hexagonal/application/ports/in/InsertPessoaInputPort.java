package com.rlti.hexagonal.application.ports.in;

import com.rlti.hexagonal.application.core.domain.Pessoa;

public interface InsertPessoaInputPort {
    void criarPessoa(Pessoa pessoa);
}
