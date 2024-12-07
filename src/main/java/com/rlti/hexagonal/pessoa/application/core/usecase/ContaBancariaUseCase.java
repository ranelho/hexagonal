package com.rlti.hexagonal.pessoa.application.core.usecase;

import com.rlti.hexagonal.pessoa.adapters.input.request.ContaBancariaRequest;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.ContaBancaria;
import com.rlti.hexagonal.pessoa.application.port.input.ContaBancariaInputPort;
import com.rlti.hexagonal.pessoa.application.port.output.ContaBancariaOutputPort;
import org.modelmapper.ModelMapper;

public class ContaBancariaUseCase implements ContaBancariaInputPort {

    private final ContaBancariaOutputPort outputPort;
    private final ModelMapper modelMapper;

    public ContaBancariaUseCase(ContaBancariaOutputPort outputPort, ModelMapper modelMapper) {
        this.outputPort = outputPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public void novaContaBancaria(Long idPessoa, ContaBancariaRequest request) {
        var contaBancaria = modelMapper.map(request, ContaBancaria.class);
        outputPort.novaContaBancaria(idPessoa, contaBancaria);
    }
}
