package com.rlti.hexagonal.application.ports.out;

import com.rlti.hexagonal.application.core.domain.Pessoa;

public interface InsertPessoaOutputPort {
    void insert(Pessoa pessoa);
}
