package com.rlti.hexagonal.pessoa.application.port.output;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pessoa;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Telefone;

import java.util.List;

public interface TelefoneOutputPort {
    void salvar(Telefone telefones);

    List<Telefone> findAllByPessoa(Pessoa pessoa);

    Telefone getById(Long id);

    void delete(Telefone telefone);

    void salvarTodos(List<Telefone> telefone);
}
