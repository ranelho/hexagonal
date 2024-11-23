package com.rlti.hexagonal.application.port.input;

import com.rlti.hexagonal.application.core.domain.Endereco;

public interface BuscaEnderecoInputPort {
    Endereco buscarEndereco(String cep);
}
