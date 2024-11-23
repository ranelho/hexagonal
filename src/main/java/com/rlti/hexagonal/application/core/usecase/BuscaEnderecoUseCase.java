package com.rlti.hexagonal.application.core.usecase;

import com.rlti.hexagonal.application.core.domain.Endereco;
import com.rlti.hexagonal.application.port.input.BuscaEnderecoInputPort;
import com.rlti.hexagonal.application.port.output.BuscaEnderecoOutputPort;

public class BuscaEnderecoUseCase implements BuscaEnderecoInputPort {

    private final BuscaEnderecoOutputPort buscaEnderecoOutputPort;

    public BuscaEnderecoUseCase(BuscaEnderecoOutputPort buscaEnderecoOutputPort) {
        this.buscaEnderecoOutputPort = buscaEnderecoOutputPort;
    }

    @Override
    public Endereco buscarEndereco(String cep) {
        if (!cep.matches("\\d{5}-?\\d{3}")) {
            throw new IllegalArgumentException("CEP inválido: " + cep);
        }

        return buscaEnderecoOutputPort.buscarEndereco(cep);
    }
}
