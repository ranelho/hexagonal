package com.rlti.hexagonal.application.port.input;

import com.rlti.hexagonal.adapters.input.controller.request.PessoaRequest;

public interface PessoaInputPort {
    void criarPessoa( PessoaRequest request);
}
