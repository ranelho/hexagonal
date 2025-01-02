package com.rlti.hexagonal.pessoa.application.core.domain.pessoa;

import lombok.Data;

@Data //todo - remove lombok
public class Agencia {
    private Long idAgencia;
    private String agencia;
    private String digitoVerificadorAgencia;
    private String nomeAgencia;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String numero;
    private Boolean indicadorInativacao;

    private Banco banco;

    public Agencia() {
    }


}
