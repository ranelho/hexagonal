package com.rlti.hexagonal.application.core.domain;

public class Cnae {
    private Long id;
    private String subclasse;
    private String denominacao;
    private Boolean principal;

    public Cnae(Long id, String subclasse, String denominacao, Boolean principal) {
        this.id = id;
        this.subclasse = subclasse;
        this.denominacao = denominacao;
        this.principal = principal;
    }

    public Cnae() {
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

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }
}
