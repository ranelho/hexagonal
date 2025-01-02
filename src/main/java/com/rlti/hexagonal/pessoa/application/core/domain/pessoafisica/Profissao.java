package com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica;

public class Profissao {
    private Long idProfissao;
    private String numeroProfissao;
    private String nomeProfissao;

    public Profissao() {
    }

    public Profissao(Long profissaoId) {
        this.idProfissao = profissaoId;
    }

    public Profissao(Long idProfissao, String numeroProfissao, String nomeProfissao) {
        this.idProfissao = idProfissao;
        this.numeroProfissao = numeroProfissao;
        this.nomeProfissao = nomeProfissao;
    }

    public Profissao(String codigo, String titulo) {
        this.numeroProfissao = codigo;
        this.nomeProfissao = titulo;
    }

    public Long getIdProfissao() {
        return idProfissao;
    }

    public void setIdProfissao(Long idProfissao) {
        this.idProfissao = idProfissao;
    }

    public String getNumeroProfissao() {
        return numeroProfissao;
    }

    public void setNumeroProfissao(String numeroProfissao) {
        this.numeroProfissao = numeroProfissao;
    }

    public String getNomeProfissao() {
        return nomeProfissao;
    }

    public void setNomeProfissao(String nomeProfissao) {
        this.nomeProfissao = nomeProfissao;
    }
}
