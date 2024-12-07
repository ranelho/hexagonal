package com.rlti.hexagonal.pessoa.application.port.input;

import com.rlti.hexagonal.pessoa.adapters.input.request.EmailRequest;
import com.rlti.hexagonal.pessoa.adapters.input.request.PessoaRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface PessoaInputPort {
    void criarPessoa(PessoaRequest request);

    Page<PessoaResponse> listarPessoas(int page, int size);

    PessoaResponse buscarPessoa(Long idPessoa);

    Page<PessoaFisicaResponse> listarPessoasFisica(int page, int size);

    PessoaFisicaDetalheResponse buscarPessoaFisica(Long idPessoa);

    PessoaJuridicaDetalheResponse buscarPessoaJuridica(Long idPessoa);

    Page<PessoaJuridicaResponse> listarPessoasJuridicas(int page, int size);

    void criarEmail(Long idPessoa, @Valid EmailRequest request);
}
