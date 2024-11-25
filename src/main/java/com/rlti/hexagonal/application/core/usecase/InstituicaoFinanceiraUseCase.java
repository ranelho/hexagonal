package com.rlti.hexagonal.application.core.usecase;

import com.rlti.hexagonal.adapters.input.controller.request.BancoRequest;
import com.rlti.hexagonal.application.core.domain.Banco;
import com.rlti.hexagonal.application.port.InstituicaoFinanceiraInputPort;
import com.rlti.hexagonal.application.port.output.InstituicaoFinanceiraOutputPort;
import org.modelmapper.ModelMapper;

public class InstituicaoFinanceiraUseCase implements InstituicaoFinanceiraInputPort {

    private final InstituicaoFinanceiraOutputPort instituicaoFinanceiraOutputPort;
    private final ModelMapper modelMapper;

    public InstituicaoFinanceiraUseCase(InstituicaoFinanceiraOutputPort instituicaoFinanceiraOutputPort, ModelMapper modelMapper) {
        this.instituicaoFinanceiraOutputPort = instituicaoFinanceiraOutputPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public void criarBanco(BancoRequest request) {
        var banco = modelMapper.map(request, Banco.class);
        instituicaoFinanceiraOutputPort.criarBanco(banco);
    }

    @Override
    public void atualizarBanco(Long id, BancoRequest request) {
        var banco = modelMapper.map(request, Banco.class);
        instituicaoFinanceiraOutputPort.atualizarBanco(id, banco);
    }
}
