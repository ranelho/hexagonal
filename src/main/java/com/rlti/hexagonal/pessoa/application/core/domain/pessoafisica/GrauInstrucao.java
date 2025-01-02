package com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica;

public class GrauInstrucao {
    private Long idGrauInstrucao;
    private String grauInstrucao;

    public GrauInstrucao() {
    }
    public GrauInstrucao(Long idGrauInstrucao) {
        this.idGrauInstrucao = idGrauInstrucao;
    }

    public GrauInstrucao(Long idGrauInstrucao, String grauInstrucao) {
        this.idGrauInstrucao = idGrauInstrucao;
        this.grauInstrucao = grauInstrucao;
    }

    public Long getIdGrauInstrucao() {
        return idGrauInstrucao;
    }

    public void setIdGrauInstrucao(Long idGrauInstrucao) {
        this.idGrauInstrucao = idGrauInstrucao;
    }

    public String getGrauInstrucao() {
        return grauInstrucao;
    }

    public void setGrauInstrucao(String grauInstrucao) {
        this.grauInstrucao = grauInstrucao;
    }
}
