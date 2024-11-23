package com.rlti.hexagonal.application.ports.in;

import com.rlti.hexagonal.adapters.in.controller.request.PessoaRequest;

public interface PessoaInputPort {
    void criarPessoa( PessoaRequest request);
}
