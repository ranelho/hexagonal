package com.rlti.hexagonal.application.core.domain;

public class CorRaca {
    private Long id;
    private String corRaca;

    public CorRaca(Long id, String corRaca) {
        this.id = id;
        this.corRaca = corRaca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorRaca() {
        return corRaca;
    }

    public void setCorRaca(String corRaca) {
        this.corRaca = corRaca;
    }
}
