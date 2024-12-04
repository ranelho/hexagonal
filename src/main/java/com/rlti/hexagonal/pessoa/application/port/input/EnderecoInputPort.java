package com.rlti.hexagonal.pessoa.application.port.input;

import com.rlti.hexagonal.pessoa.adapters.input.controller.request.EnderecoRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.EnderecoResponse;
import com.rlti.hexagonal.pessoa.application.core.domain.Endereco;

public interface EnderecoInputPort {
    EnderecoResponse buscarEndereco(String cep);

    void salvarEndereco(Long idPessoa, EnderecoRequest endereco);
}
