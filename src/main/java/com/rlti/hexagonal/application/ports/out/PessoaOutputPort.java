package com.rlti.hexagonal.application.ports.out;

import com.rlti.hexagonal.application.core.domain.Pessoa;

public interface PessoaOutputPort {
    void salvar(Pessoa pessoa);
}
