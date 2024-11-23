package com.rlti.hexagonal.application.core.usecase;

import com.rlti.hexagonal.adapters.input.controller.request.PessoaRequest;
import com.rlti.hexagonal.adapters.output.response.PessoaResponse;
import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.port.input.PessoaInputPort;
import com.rlti.hexagonal.application.port.output.PessoaOutputPort;
import org.springframework.data.domain.Page;

public class PessoaUserCase implements PessoaInputPort {

    private final PessoaOutputPort pessoaOutputPort;

    public PessoaUserCase(PessoaOutputPort pessoaOutputPort) {
        this.pessoaOutputPort = pessoaOutputPort;
    }

    @Override
    public void criarPessoa(PessoaRequest request) {
        var pessoa = new Pessoa(request);
        pessoaOutputPort.salvar(pessoa);
    }

    @Override
    public Page<PessoaResponse> listarPessoas(int page, int size) {
        var pessoas = pessoaOutputPort.findAllPaginated(page, size);
        return PessoaResponse.converte(pessoas);
    }

}
