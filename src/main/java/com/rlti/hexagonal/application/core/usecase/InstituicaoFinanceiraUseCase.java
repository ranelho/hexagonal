package com.rlti.hexagonal.application.core.usecase;

import com.rlti.hexagonal.adapters.input.controller.request.AgenciaRequest;
import com.rlti.hexagonal.adapters.input.controller.request.BancoRequest;
import com.rlti.hexagonal.adapters.output.response.BancoResponse;
import com.rlti.hexagonal.application.core.domain.Agencia;
import com.rlti.hexagonal.application.core.domain.Banco;
import com.rlti.hexagonal.application.port.InstituicaoFinanceiraInputPort;
import com.rlti.hexagonal.application.port.output.InstituicaoFinanceiraOutputPort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

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

    @Override
    public Page<BancoResponse> listarBancos(int page, int size) {
        var bancos = instituicaoFinanceiraOutputPort.findAllPaginated(page, size);
        return bancos.map(banco -> modelMapper.map(banco, BancoResponse.class));
    }

    @Override
    public BancoResponse buscarBanco(Long id) {
        var banco = instituicaoFinanceiraOutputPort.findById(id);
        return modelMapper.map(banco, BancoResponse.class);
    }

    @Override
    public void criarAgencia(Long id, AgenciaRequest request) {
        var agencia = modelMapper.map(request, Agencia.class);
        instituicaoFinanceiraOutputPort.criarAgencia(id, agencia);
    }


}
