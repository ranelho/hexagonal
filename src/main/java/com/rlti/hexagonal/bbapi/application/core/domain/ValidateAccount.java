package com.rlti.hexagonal.bbapi.application.core.domain;

public class ValidateAccount {
    private String contaAtiva;
    private String titularConta;

    public ValidateAccount(){}

    public ValidateAccount(String contaAtiva, String titularConta) {
        this.contaAtiva = contaAtiva;
        this.titularConta = titularConta;
    }

    public String getContaAtiva() {
        return contaAtiva;
    }

    public void setContaAtiva(String contaAtiva) {
        this.contaAtiva = contaAtiva;
    }

    public String getTitularConta() {
        return titularConta;
    }

    public void setTitularConta(String titularConta) {
        this.titularConta = titularConta;
    }
}
