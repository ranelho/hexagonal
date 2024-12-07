package com.rlti.hexagonal.pessoa.adapters.input.request;

import lombok.Data;

@Data
public class BancoRequest {
    private String nome;
    private String codigo;
    private String cnpj;
    private String inscricaoEstadual;
}
