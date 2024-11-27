package com.rlti.hexagonal.application.port.input;

import com.rlti.hexagonal.adapters.input.controller.request.EnderecoRequest;
import com.rlti.hexagonal.application.core.domain.Endereco;

public interface EnderecoInputPort {
    Endereco buscarEndereco(String cep);

    void salvarEndereco(Long idPessoaFisica, EnderecoRequest endereco);
}
