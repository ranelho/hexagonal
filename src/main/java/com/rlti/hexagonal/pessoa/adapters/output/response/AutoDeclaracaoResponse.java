package com.rlti.hexagonal.pessoa.adapters.output.response;

import lombok.Data;

@Data
public class AutoDeclaracaoResponse {
    private String identidadeGenero;
    private String especificacaoIg;
    private String orientacaoSexual;
    private String especificacaoOs;
    private String corRaca;

    public AutoDeclaracaoResponse(String identidadeGenero, String especificacaoIg, String orientacaoSexual, String especificacaoOs, String corRaca) {
        this.identidadeGenero = identidadeGenero;
        this.especificacaoIg = especificacaoIg;
        this.orientacaoSexual = orientacaoSexual;
        this.especificacaoOs = especificacaoOs;
        this.corRaca = corRaca;
    }
}
