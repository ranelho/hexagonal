package com.rlti.hexagonal.application.core.usecase;

import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.ports.in.InsertPessoaInputPort;
import com.rlti.hexagonal.application.ports.out.InsertPessoaOutputPort;

public class InsertPessoaUserCase implements InsertPessoaInputPort {

    private final InsertPessoaOutputPort insertPessoaOutputPort;

    public InsertPessoaUserCase(InsertPessoaOutputPort insertPessoaOutputPort) {
        this.insertPessoaOutputPort = insertPessoaOutputPort;
    }

    @Override
    public void criarPessoa(Pessoa pessoa) {
        insertPessoaOutputPort.insert(pessoa);
    }
}
