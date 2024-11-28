package com.rlti.hexagonal.adapters.output.response;

import com.rlti.hexagonal.application.core.domain.PessoaJuridica;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class PessoaJuridicaResponse {
    private Long id;
    private String cnpj;
    private String nome;
    private String razaoSocial;

    public PessoaJuridicaResponse(PessoaJuridica pessoaJuridica) {
        this.id = pessoaJuridica.getIdPessoaJuridica();
        this.cnpj = pessoaJuridica.getCnpj();
        this.nome = pessoaJuridica.getPessoa().getNome();
        this.razaoSocial = pessoaJuridica.getRazaoSocial();
    }

    public static Page<PessoaJuridicaResponse> converte(Page<PessoaJuridica> pessoas) {
        return pessoas.map(PessoaJuridicaResponse::new);
    }
}
