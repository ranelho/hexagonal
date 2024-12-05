package com.rlti.hexagonal.pessoa.application.port.output;

import com.rlti.hexagonal.pessoa.application.core.domain.Endereco;

public interface BuscaEnderecoOutputPort {
    Endereco buscarEndereco(String cep);
}
