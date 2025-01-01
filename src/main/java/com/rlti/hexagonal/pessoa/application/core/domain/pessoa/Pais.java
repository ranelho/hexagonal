package com.rlti.hexagonal.pessoa.application.core.domain.pessoa;

import jakarta.validation.constraints.NotNull;

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

    public Pais(@NotNull Long paisId) {
        this.idPais = paisId;
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
