package com.rlti.hexagonal.pessoa.application.port.output;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Endereco;

import java.util.List;

public interface EnderecoOutputPort {
    void salvarEndereco(Long idPessoa, Endereco endereco);


}
