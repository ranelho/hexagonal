package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.PessoaJuridica;
import org.springframework.data.domain.Page;

public record PessoaJuridicaResponse(
        Long id,
        String cnpj,
        String nome,
        String razaoSocial
) {
    public PessoaJuridicaResponse(PessoaJuridica pessoaJuridica) {
        this(
                pessoaJuridica.getIdPessoaJuridica(),
                pessoaJuridica.getCnpj(),
                pessoaJuridica.getPessoa().getNome(),
                pessoaJuridica.getRazaoSocial()
        );
    }

    public static Page<PessoaJuridicaResponse> converte(Page<PessoaJuridica> pessoas) {
        return pessoas.map(PessoaJuridicaResponse::new);
    }
}
