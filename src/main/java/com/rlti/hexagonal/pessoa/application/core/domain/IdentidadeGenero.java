package com.rlti.hexagonal.pessoa.application.core.domain;

public class IdentidadeGenero {
    private Long idIdentidadeDeGenero;
    private String identidadeDeGenero;

    public IdentidadeGenero() {
    }

    public IdentidadeGenero(Long idIdentidadeDeGenero, String identidadeDeGenero) {
        this.idIdentidadeDeGenero = idIdentidadeDeGenero;
        this.identidadeDeGenero = identidadeDeGenero;
    }

    public Long getIdIdentidadeDeGenero() {
        return idIdentidadeDeGenero;
    }

    public void setIdIdentidadeDeGenero(Long idIdentidadeDeGenero) {
        this.idIdentidadeDeGenero = idIdentidadeDeGenero;
    }

    public String getIdentidadeDeGenero() {
        return identidadeDeGenero;
    }

    public void setIdentidadeDeGenero(String identidadeDeGenero) {
        this.identidadeDeGenero = identidadeDeGenero;
    }
}
