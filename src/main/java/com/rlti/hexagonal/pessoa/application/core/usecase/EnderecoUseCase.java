package com.rlti.hexagonal.pessoa.application.core.usecase;

import com.rlti.hexagonal.pessoa.adapters.input.controller.request.EnderecoRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.EnderecoResponse;
import com.rlti.hexagonal.pessoa.application.core.domain.Endereco;
import com.rlti.hexagonal.pessoa.application.port.input.EnderecoInputPort;
import com.rlti.hexagonal.pessoa.application.port.output.BuscaEnderecoOutputPort;
import com.rlti.hexagonal.pessoa.application.port.output.EnderecoOutputPort;
import org.modelmapper.ModelMapper;

public class EnderecoUseCase implements EnderecoInputPort {

    private final BuscaEnderecoOutputPort buscaEnderecoOutputPort;
    private final EnderecoOutputPort enderecoOutputPort;
    private final ModelMapper modelMapper;

    public EnderecoUseCase(BuscaEnderecoOutputPort buscaEnderecoOutputPort, EnderecoOutputPort enderecoOutputPort, ModelMapper modelMapper) {
        this.buscaEnderecoOutputPort = buscaEnderecoOutputPort;
        this.enderecoOutputPort = enderecoOutputPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public EnderecoResponse buscarEndereco(String cep) {
        if (!cep.matches("\\d{5}-?\\d{3}")) {
            throw new IllegalArgumentException("CEP inválido: " + cep);
        }

        return buscaEnderecoOutputPort.buscarEndereco(cep);
    }

    @Override
    public void salvarEndereco(Long idPessoaFisica, EnderecoRequest request) {
        var endereco = modelMapper.map(request, Endereco.class);
        enderecoOutputPort.salvarEndereco(idPessoaFisica, endereco);
    }
}
