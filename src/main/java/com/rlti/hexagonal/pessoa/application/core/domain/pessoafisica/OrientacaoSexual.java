package com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica;

public class OrientacaoSexual {
    private Long idOrientacaoSexual;
    private String orientacaoSexual;

    public OrientacaoSexual() {
    }

    public OrientacaoSexual(Long idOrientacaoSexual, String orientacaoSexual) {
        this.idOrientacaoSexual = idOrientacaoSexual;
        this.orientacaoSexual = orientacaoSexual;
    }

    public OrientacaoSexual(Long orientacaoSexualId) {
        this.idOrientacaoSexual = orientacaoSexualId;
    }

    public Long getIdOrientacaoSexual() {
        return idOrientacaoSexual;
    }

    public void setIdOrientacaoSexual(Long idOrientacaoSexual) {
        this.idOrientacaoSexual = idOrientacaoSexual;
    }

    public String getOrientacaoSexual() {
        return orientacaoSexual;
    }

    public void setOrientacaoSexual(String orientacaoSexual) {
        this.orientacaoSexual = orientacaoSexual;
    }
}
