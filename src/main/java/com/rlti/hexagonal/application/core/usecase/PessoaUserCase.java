package com.rlti.hexagonal.application.core.usecase;

import com.rlti.hexagonal.adapters.input.controller.request.PessoaFisicaRequest;
import com.rlti.hexagonal.adapters.input.controller.request.PessoaRequest;
import com.rlti.hexagonal.adapters.output.response.PessoaFisicaDetalheResponse;
import com.rlti.hexagonal.adapters.output.response.PessoaFisicaResponse;
import com.rlti.hexagonal.adapters.output.response.PessoaJuridicaDetalheResponse;
import com.rlti.hexagonal.adapters.output.response.PessoaResponse;
import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.core.domain.PessoaFisica;
import com.rlti.hexagonal.application.port.input.PessoaInputPort;
import com.rlti.hexagonal.application.port.output.PessoaOutputPort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class PessoaUserCase implements PessoaInputPort {

    private final PessoaOutputPort pessoaOutputPort;
    private final ModelMapper modelMapper;

    public PessoaUserCase(PessoaOutputPort pessoaOutputPort, ModelMapper modelMapper) {
        this.pessoaOutputPort = pessoaOutputPort;
        this.modelMapper = modelMapper;
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

    @Override
    public PessoaResponse buscarPessoa(Long id) {
        var pessoa = pessoaOutputPort.findPessoaById(id);
        return new PessoaResponse(pessoa);
    }

    @Override
    public Page<PessoaFisicaResponse> listarPessoasFisica(int page, int size) {
        var pessoasFisica = pessoaOutputPort.findAllPessoaFisicaPaginated(page, size);
        return PessoaFisicaResponse.converte(pessoasFisica);
    }

    @Override
    public PessoaFisicaDetalheResponse buscarPessoaFisica(Long id) {
        var pessoaFisica = pessoaOutputPort.findPessoaFisicaById(id);
        return new PessoaFisicaDetalheResponse(pessoaFisica);
    }

    @Override
    public PessoaJuridicaDetalheResponse buscarPessoaJuridica(Long idPessoa) {
        var pessoaJuridica = pessoaOutputPort.findPessoaJuridicaById(idPessoa);
        return new PessoaJuridicaDetalheResponse(pessoaJuridica);
    }

}
