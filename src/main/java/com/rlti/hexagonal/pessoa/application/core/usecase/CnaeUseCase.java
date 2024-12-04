package com.rlti.hexagonal.pessoa.application.core.usecase;

import com.rlti.hexagonal.pessoa.adapters.input.controller.request.CnaeRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.CnaeResponse;
import com.rlti.hexagonal.pessoa.application.core.domain.Cnae;
import com.rlti.hexagonal.pessoa.application.port.input.CnaeInputPort;
import com.rlti.hexagonal.pessoa.application.port.output.CnaeOutputPort;
import org.modelmapper.ModelMapper;

import java.util.List;

public class CnaeUseCase implements CnaeInputPort {
    private final CnaeOutputPort outputPort;
    private final ModelMapper modelMapper;


    public CnaeUseCase(CnaeOutputPort outputPort, ModelMapper modelMapper) {
        this.outputPort = outputPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CnaeResponse> findAll() {
        var cnaes = outputPort.findAll();
        return CnaeResponse.convert(cnaes);
    }

    @Override
    public void addCnaes(Long idPessoa, List<CnaeRequest> cnaeRequestList) {
        var cnaes = cnaeRequestList.stream()
                .map(request -> modelMapper.map(request, Cnae.class))
                .toList();
        long principais = cnaes.stream()
                .filter(Cnae::isPrincipal)
                .count();
        if (principais > 1) {
            throw new IllegalArgumentException("Apenas um CNAE pode ser marcado como principal.");
        }
        outputPort.addCnaes(idPessoa, cnaes);
    }

}
