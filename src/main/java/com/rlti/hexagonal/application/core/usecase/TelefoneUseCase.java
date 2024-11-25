package com.rlti.hexagonal.application.core.usecase;

import com.rlti.hexagonal.adapters.input.controller.request.TelefoneRequest;
import com.rlti.hexagonal.adapters.output.response.TelefoneResponse;
import com.rlti.hexagonal.application.core.domain.Telefone;
import com.rlti.hexagonal.application.port.input.TelefoneInputPort;
import com.rlti.hexagonal.application.port.output.PessoaOutputPort;
import com.rlti.hexagonal.application.port.output.TelefoneOutputPort;

import java.util.List;

public class TelefoneUseCase implements TelefoneInputPort {

    private final PessoaOutputPort pessoaOutputPort;
    private final TelefoneOutputPort telefoneOutputPort;

    public TelefoneUseCase(PessoaOutputPort pessoaOutputPort, TelefoneOutputPort telefoneOutputPort) {
        this.pessoaOutputPort = pessoaOutputPort;
        this.telefoneOutputPort = telefoneOutputPort;
    }

    @Override
    public void criarTelefone(Long id, TelefoneRequest request) {
        var pessoa = pessoaOutputPort.findById(id);
        var telefone = new Telefone(request, pessoa);
        telefoneOutputPort.salvar(telefone);
    }

    @Override
    public List<TelefoneResponse> findAllByCpf(Long id) {
        var pessoa = pessoaOutputPort.findById(id);
        var telefones=  telefoneOutputPort.findAllByPessoaFisica(pessoa);
        return TelefoneResponse.converte(telefones);
    }

    @Override
    public void updateTelefone(Long id, TelefoneRequest request) {
        var telefone = getTelefoneById(id);
        telefone.update(request);
        telefoneOutputPort.salvar(telefone);
    }

    @Override
    public void deleteTelefone(Long id) {
        var telefone = getTelefoneById(id);
        telefoneOutputPort.delete(telefone);
    }

    private Telefone getTelefoneById(Long id) {
        return telefoneOutputPort.getById(id);
    }

}
