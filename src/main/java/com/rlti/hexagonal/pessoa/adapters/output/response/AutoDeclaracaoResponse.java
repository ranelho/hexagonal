package com.rlti.hexagonal.pessoa.adapters.output.response;

import lombok.Data;

@Data
public class AutoDeclaracaoResponse {
    private String especificacaoPpe;
    private String especificacaoIg;
    private String especificacaoOs;
    private String corRaca;

    public AutoDeclaracaoResponse(String especificacaoPpe, String especificacaoIg, String especificacaoOs, String corRaca) {
        this.especificacaoPpe = especificacaoPpe;
        this.especificacaoIg = especificacaoIg;
        this.especificacaoOs = especificacaoOs;
        this.corRaca = corRaca;
    }
}
