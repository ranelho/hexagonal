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
    public void criarTelefone(String cpf, TelefoneRequest request) {
        var pessoaFisica = pessoaOutputPort.getByCpf(cpf);
        var telefone = new Telefone(request, pessoaFisica);
        telefoneOutputPort.salvar(telefone);
    }

    @Override
    public List<TelefoneResponse> findAllByCpf(String cpf) {
        var pessoaFisica = pessoaOutputPort.getByCpf(cpf);
        var telefones=  telefoneOutputPort.findAllByPessoaFisica(pessoaFisica);
        return TelefoneResponse.converte(telefones);
    }
}
