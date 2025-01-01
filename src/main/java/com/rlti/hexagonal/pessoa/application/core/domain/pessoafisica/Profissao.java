package com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica;

public class Profissao {
    private Long codigo;
    private String nomeProfissao;

    public Profissao() {
    }

    public Profissao(Long codigo, String nomeProfissao) {
        this.codigo = codigo;
        this.nomeProfissao = nomeProfissao;
    }

    public Profissao(Long profissaoId) {
        this.codigo = profissaoId;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNomeProfissao() {
        return nomeProfissao;
    }

    public void setNomeProfissao(String nomeProfissao) {
        this.nomeProfissao = nomeProfissao;
    }
}
