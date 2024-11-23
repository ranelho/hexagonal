package com.rlti.hexagonal.application.port.output;

import com.rlti.hexagonal.application.core.domain.Pessoa;

public interface PessoaOutputPort {
    void salvar(Pessoa pessoa);
}
