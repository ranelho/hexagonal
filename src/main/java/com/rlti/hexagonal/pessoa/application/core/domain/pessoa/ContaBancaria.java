package com.rlti.hexagonal.pessoa.application.core.domain.pessoa;

public class ContaBancaria {
    private Long idAgencia;
    private Long idContaBancaria;
    private String numeroConta;
    private String digitoVerificador;
    private String tipoChavePix;
    private String chavePix;

    public ContaBancaria() {
    }

    public ContaBancaria(Long idAgencia, Long idContaBancaria, String numeroConta, String digitoVerificador, String tipoChavePix, String chavePix) {
        this.idAgencia = idAgencia;
        this.idContaBancaria = idContaBancaria;
        this.numeroConta = numeroConta;
        this.digitoVerificador = digitoVerificador;
        this.tipoChavePix = tipoChavePix;
        this.chavePix = chavePix;
    }

    public Long getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Long idAgencia) {
        this.idAgencia = idAgencia;
    }

    public Long getIdContaBancaria() {
        return idContaBancaria;
    }

    public void setIdContaBancaria(Long idContaBancaria) {
        this.idContaBancaria = idContaBancaria;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitoVerificador(String digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    public String getTipoChavePix() {
        return tipoChavePix;
    }

    public void setTipoChavePix(String tipoChavePix) {
        this.tipoChavePix = tipoChavePix;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }
}
