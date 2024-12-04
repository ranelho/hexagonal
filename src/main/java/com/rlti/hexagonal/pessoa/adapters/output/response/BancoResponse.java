package com.rlti.hexagonal.pessoa.adapters.output.response;

import lombok.Data;

@Data
public class BancoResponse {
    private Long idBanco;
    private String nome;
    private String codigo;
}