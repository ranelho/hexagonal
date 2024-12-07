package com.rlti.hexagonal.pessoa.application.core.usecase;

import com.rlti.hexagonal.pessoa.adapters.input.request.EmailRequest;
import com.rlti.hexagonal.pessoa.adapters.input.request.PessoaRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.*;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Email;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pessoa;
import com.rlti.hexagonal.pessoa.application.port.input.PessoaInputPort;
import com.rlti.hexagonal.pessoa.application.port.output.PessoaOutputPort;
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
    public PessoaResponse buscarPessoa(Long idPessoa) {
        var pessoa = pessoaOutputPort.findPessoaById(idPessoa);
        return new PessoaResponse(pessoa);
    }

    @Override
    public Page<PessoaFisicaResponse> listarPessoasFisica(int page, int size) {
        var pessoasFisica = pessoaOutputPort.findAllPessoaFisicaPaginated(page, size);
        return PessoaFisicaResponse.converte(pessoasFisica);
    }

    @Override
    public PessoaFisicaDetalheResponse buscarPessoaFisica(Long idPessoa) {
        var pessoaFisica = pessoaOutputPort.findPessoaFisicaById(idPessoa);
        return new PessoaFisicaDetalheResponse(pessoaFisica);
    }

    @Override
    public PessoaJuridicaDetalheResponse buscarPessoaJuridica(Long idPessoa) {
        var pessoaJuridica = pessoaOutputPort.findPessoaJuridicaById(idPessoa);
        return new PessoaJuridicaDetalheResponse(pessoaJuridica);
    }

    @Override
    public Page<PessoaJuridicaResponse> listarPessoasJuridicas(int page, int size) {
        var pessoasJuridicas = pessoaOutputPort.findAllPessoaJuridicaPaginated(page, size);
        return PessoaJuridicaResponse.converte(pessoasJuridicas);
    }

    @Override
    public void criarEmail(Long idPessoa, EmailRequest request) {
        var email = modelMapper.map(request, Email.class);
        pessoaOutputPort.salvarEmail(idPessoa, email);
    }


}
