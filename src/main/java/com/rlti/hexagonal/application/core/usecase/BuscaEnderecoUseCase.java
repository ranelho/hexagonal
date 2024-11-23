package com.rlti.hexagonal.application.core.usecase;

import com.rlti.hexagonal.application.core.domain.Endereco;
import com.rlti.hexagonal.application.port.output.BuscaEnderecoInputPort;
import com.rlti.hexagonal.application.port.output.ViaCepOutputPort;

public class BuscaEnderecoUseCase implements BuscaEnderecoInputPort {

    private final ViaCepOutputPort viaCepOutputPort;

    public BuscaEnderecoUseCase(ViaCepOutputPort viaCepOutputPort) {
        this.viaCepOutputPort = viaCepOutputPort;
    }

    @Override
    public Endereco buscarEndereco(String cep) {
        if (!cep.matches("\\d{5}-?\\d{3}")) {
            throw new IllegalArgumentException("CEP inválido: " + cep);
        }

        return viaCepOutputPort.buscarEndereco(cep);
    }
}
