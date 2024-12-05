package com.rlti.hexagonal.pessoa.application.core.domain;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoEndereco;

public class Endereco {
    private Long id;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private TipoEndereco tipoEndereco;
    private String indicadorCorrespondencia;
    private String indicadorFaturamento;

    public Endereco() {
    }

    public Endereco(Long id, String cep, String logradouro, String numero, String complemento, String bairro, String localidade, String uf, TipoEndereco tipoEndereco, String indicadorCorrespondencia, String indicadorFaturamento) {
        this.id = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.tipoEndereco = tipoEndereco;
        this.indicadorCorrespondencia = indicadorCorrespondencia;
        this.indicadorFaturamento = indicadorFaturamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getIndicadorCorrespondencia() {
        return indicadorCorrespondencia;
    }

    public void setIndicadorCorrespondencia(String indicadorCorrespondencia) {
        this.indicadorCorrespondencia = indicadorCorrespondencia;
    }

    public String getIndicadorFaturamento() {
        return indicadorFaturamento;
    }

    public void setIndicadorFaturamento(String indicadorFaturamento) {
        this.indicadorFaturamento = indicadorFaturamento;
    }
}
