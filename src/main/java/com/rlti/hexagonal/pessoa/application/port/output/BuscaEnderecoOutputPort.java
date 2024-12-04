package com.rlti.hexagonal.pessoa.application.port.output;

import com.rlti.hexagonal.pessoa.adapters.output.response.EnderecoResponse;

public interface BuscaEnderecoOutputPort {
    EnderecoResponse buscarEndereco(String cep);
}
