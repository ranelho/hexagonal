package com.rlti.hexagonal.application.port.output;

import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.core.domain.PessoaFisica;
import com.rlti.hexagonal.application.core.domain.Telefone;

import java.util.List;

public interface TelefoneOutputPort {
    void salvar(Telefone telefone);

    List<Telefone> findAllByPessoaFisica(Pessoa pessoa);

    Telefone getById(Long id);

    void delete(Telefone telefone);
}
