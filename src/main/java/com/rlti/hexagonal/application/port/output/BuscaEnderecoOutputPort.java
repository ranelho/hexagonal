package com.rlti.hexagonal.application.port.output;

import com.rlti.hexagonal.application.core.domain.Endereco;

public interface BuscaEnderecoOutputPort {
    Endereco buscarEndereco(String cep);
}
