package com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica;

public class IdentidadeGenero {
    private Long id;
    private String genero;

    public IdentidadeGenero() {
    }

    public IdentidadeGenero(Long id) {
        this.id = id;
    }

    public IdentidadeGenero(Long id, String genero) {
        this.id = id;
        this.genero = genero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
