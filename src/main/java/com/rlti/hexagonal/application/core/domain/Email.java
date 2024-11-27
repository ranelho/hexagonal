package com.rlti.hexagonal.application.core.domain;

import com.rlti.hexagonal.adapters.input.controller.request.EmailRequest;
import com.rlti.hexagonal.application.core.domain.enums.TipoEmail;

import java.util.List;

public class Email {
    private Long idEmail;
    private String email;
    private TipoEmail tipoEmail;

    public Email(Long idEmail, String email, TipoEmail tipoEmail) {
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

    public TipoEmail getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(TipoEmail tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    public List<Email> converteList(List<EmailRequest> emails) {
        return emails.stream().map(emailRequest -> new Email(null, emailRequest.getEmail(), emailRequest.getTipoEmail())).toList();
    }
}
