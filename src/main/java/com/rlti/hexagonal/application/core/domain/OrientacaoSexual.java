package com.rlti.hexagonal.application.core.domain;

public class OrientacaoSexual {
    private Long idOrientacaoSexual;
    private String descricao;

    public OrientacaoSexual() {
    }

    public OrientacaoSexual(Long idOrientacaoSexual, String descricao) {
        this.idOrientacaoSexual = idOrientacaoSexual;
        this.descricao = descricao;
    }

    public Long getIdOrientacaoSexual() {
        return idOrientacaoSexual;
    }

    public void setIdOrientacaoSexual(Long idOrientacaoSexual) {
        this.idOrientacaoSexual = idOrientacaoSexual;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
