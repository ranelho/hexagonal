package com.rlti.hexagonal.application.core.usecase;

import com.rlti.hexagonal.adapters.in.controller.request.PessoaRequest;
import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.ports.in.PessoaInputPort;
import com.rlti.hexagonal.application.ports.out.PessoaOutputPort;

public class PessoaUserCase implements PessoaInputPort {

    private final PessoaOutputPort pessoaOutputPort;

    public PessoaUserCase(PessoaOutputPort pessoaOutputPort) {
        this.pessoaOutputPort = pessoaOutputPort;
    }

    @Override
    public void criarPessoa(PessoaRequest request) {
        var pessoa = new Pessoa(request);
        pessoaOutputPort.salvar(pessoa);
    }
}
