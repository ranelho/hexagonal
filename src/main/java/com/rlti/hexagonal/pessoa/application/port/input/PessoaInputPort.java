package com.rlti.hexagonal.pessoa.application.port.input;

import com.rlti.hexagonal.pessoa.adapters.input.request.*;
import com.rlti.hexagonal.pessoa.adapters.output.response.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaInputPort {
   /* Long criarPessoa(PessoaRequest request);*/

    Long criarPessoaFisica(@Valid PessoaFisicaRequest request);

    Page<PessoaResponse> listarPessoas(int page, int size);

    PessoaResponse buscarPessoa(Long idPessoa);

    Page<PessoaFisicaResponse> listarPessoasFisica(Pageable pageable);

    PessoaFisicaDetalheResponse buscarPessoaFisica(Long idPessoa);

    PessoaJuridicaDetalheResponse buscarPessoaJuridica(Long idPessoa);

    Page<PessoaJuridicaResponse> listarPessoasJuridicas(int page, int size);

    void addEmailPessoa(Long idPessoa, @Valid EmailRequest request);

    void atualizarPessoa(Long idPessoa, @Valid PessoaFisicaUpdateRequest request);

    Page<PessoaFisicaResponse> listPessoasFisicasWithFilter(Pageable pageable, PessoaFilterParam params);

    Page<PessoaFisicaDTO> listPessoasFisicasWithFilter2(Pageable pageable, PessoaFilterParam params);
}
