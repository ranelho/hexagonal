package com.rlti.hexagonal.application.core.domain;

public class Email {
    private Long idEmail;
    private String email;
    private String tipoEmail;

    public Email(Long idEmail, String email, String tipoEmail) {
        this.idEmail = idEmail;
        this.email = email;
        this.tipoEmail = tipoEmail;
    }

    public Email() {
    }

    public Long getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(Long idEmail) {
        this.idEmail = idEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(String tipoEmail) {
        this.tipoEmail = tipoEmail;
    }
}
