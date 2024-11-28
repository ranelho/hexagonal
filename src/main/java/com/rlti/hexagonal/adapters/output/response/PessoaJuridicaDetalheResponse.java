package com.rlti.hexagonal.adapters.output.response;

import com.rlti.hexagonal.application.core.domain.PessoaJuridica;
import lombok.Data;

@Data
public class PessoaJuridicaDetalheResponse {
    private Long id;
    private String cnpj;
    private String nome;
    private String razaoSocial;

    public PessoaJuridicaDetalheResponse(PessoaJuridica pessoaJuridica) {
        this.id = pessoaJuridica.getPessoa().getIdPessoa();
        this.cnpj = pessoaJuridica.getCnpj();
        this.nome = pessoaJuridica.getPessoa().getNome();
        this.razaoSocial = pessoaJuridica.getRazaoSocial();
    }
}
