package com.rlti.hexagonal.pessoa.application.core.usecase;

import com.rlti.hexagonal.pessoa.adapters.input.controller.request.TelefoneRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.TelefoneResponse;
import com.rlti.hexagonal.pessoa.application.core.domain.Telefone;
import com.rlti.hexagonal.pessoa.application.port.input.TelefoneInputPort;
import com.rlti.hexagonal.pessoa.application.port.output.PessoaOutputPort;
import com.rlti.hexagonal.pessoa.application.port.output.TelefoneOutputPort;

import java.util.List;

public class TelefoneUseCase implements TelefoneInputPort {

    private final PessoaOutputPort pessoaOutputPort;
    private final TelefoneOutputPort telefoneOutputPort;

    public TelefoneUseCase(PessoaOutputPort pessoaOutputPort, TelefoneOutputPort telefoneOutputPort) {
        this.pessoaOutputPort = pessoaOutputPort;
        this.telefoneOutputPort = telefoneOutputPort;
    }

    @Override
    public void criarTelefone(Long idPessoa, List<TelefoneRequest> telefones) {
        var pessoa = pessoaOutputPort.findPessoaById(idPessoa);
        var telefonesList = Telefone.converte(telefones, pessoa);
        telefoneOutputPort.salvarTodos(telefonesList);
    }

    @Override
    public List<TelefoneResponse> findAllById(Long idPessoa) {
        var pessoa = pessoaOutputPort.findPessoaById(idPessoa);
        var telefones = telefoneOutputPort.findAllByPessoa(pessoa);
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
