package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.PessoaFisica;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public record PessoaFisicaResponse(
        String cpf,
        String nome,
        LocalDate dataNascimento,
        String sexo) {

    public PessoaFisicaResponse(PessoaFisica pessoaFisica) {
        this(
                pessoaFisica.getCpf(),
                pessoaFisica.getPessoa().getNome(),
                pessoaFisica.getDataNascimento(),
                pessoaFisica.getSexo().toString()
        );
    }

    public static Page<PessoaFisicaResponse> converte(Page<PessoaFisica> pessoas) {
        return pessoas.map(PessoaFisicaResponse::new);
    }
}