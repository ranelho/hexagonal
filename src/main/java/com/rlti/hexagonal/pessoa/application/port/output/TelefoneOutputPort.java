package com.rlti.hexagonal.pessoa.application.port.output;

import com.rlti.hexagonal.pessoa.application.core.domain.PessoaFisica;
import com.rlti.hexagonal.pessoa.application.core.domain.Telefone;

import java.util.List;

public interface TelefoneOutputPort {
    void salvar(Telefone telefones);

    List<Telefone> findAllByPessoaFisica(PessoaFisica pessoa);

    Telefone getById(Long id);

    void delete(Telefone telefone);

    void salvarTodos(List<Telefone> telefone);
}
