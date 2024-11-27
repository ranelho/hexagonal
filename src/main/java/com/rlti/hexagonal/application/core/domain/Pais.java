package com.rlti.hexagonal.application.core.domain;

public class Pais {
    private Long idPais;
    private String nome;
    private String sigla;
    private String nacionalidade;

    public Pais() {
    }

    public Pais(Long idPais, String nome, String sigla, String nacionalidade) {
        this.idPais = idPais;
        this.nome = nome;
        this.sigla = sigla;
        this.nacionalidade = nacionalidade;
    }

    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
