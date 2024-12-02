package com.rlti.hexagonal.application.core.domain;

public class Cnae {
    private Long id;
    private String subclasse;
    private String denominacao;
    private boolean isPrincipal;

    public Cnae() {
    }

    public Cnae(Long id, String subclasse, String denominacao, boolean isPrincipal) {
        this.id = id;
        this.subclasse = subclasse;
        this.denominacao = denominacao;
        this.isPrincipal = isPrincipal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubclasse() {
        return subclasse;
    }

    public void setSubclasse(String subclasse) {
        this.subclasse = subclasse;
    }

    public String getDenominacao() {
        return denominacao;
    }

    public void setDenominacao(String denominacao) {
        this.denominacao = denominacao;
    }

    public boolean isPrincipal() {
        return isPrincipal;
    }

    public void setPrincipal(boolean principal) {
        isPrincipal = principal;
    }
}
