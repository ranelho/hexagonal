package com.rlti.hexagonal.pessoa.adapters.input.request;

import lombok.Data;

@Data
public class AgenciaRequest {
    private String agencia;
    private String digitoVerificadorAgencia;
    private String nomeAgencia;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String numero;
}
