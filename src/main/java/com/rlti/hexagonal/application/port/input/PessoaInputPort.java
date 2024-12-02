package com.rlti.hexagonal.application.port.input;

import com.rlti.hexagonal.adapters.input.controller.request.PessoaRequest;
import com.rlti.hexagonal.adapters.output.response.*;
import org.springframework.data.domain.Page;

public interface PessoaInputPort {
    void criarPessoa(PessoaRequest request);

    Page<PessoaResponse> listarPessoas(int page, int size);

    PessoaResponse buscarPessoa(Long idPessoa);

    Page<PessoaFisicaResponse> listarPessoasFisica(int page, int size);

    PessoaFisicaDetalheResponse buscarPessoaFisica(Long idPessoa);

    PessoaJuridicaDetalheResponse buscarPessoaJuridica(Long idPessoa);

    Page<PessoaJuridicaResponse> listarPessoasJuridicas(int page, int size);

}
