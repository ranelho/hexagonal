package com.rlti.hexagonal.application.core.domain;

public class Profissao {
    private Long id;
    private String nomeProfissao;

    public Profissao() {
    }

    public Profissao(Long id, String nomeProfissao) {
        this.id = id;
        this.nomeProfissao = nomeProfissao;
    }

    public Long getId() {
        return id;
    }

    public String getNomeProfissao() {
        return nomeProfissao;
    }
}
