package com.rlti.hexagonal.pessoa.application.core.domain.pessoa;

import com.rlti.hexagonal.pessoa.adapters.input.request.EmailRequest;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoEmail;

public class Email {
    private Long idEmail;
    private String email;
    private TipoEmail tipoEmail;

    private Pessoa pessoa;

    public Email(Long idEmail, String email, TipoEmail tipoEmail) {
        this.idEmail = idEmail;
        this.email = email;
        this.tipoEmail = tipoEmail;
    }
    public Email() {
    }

    public Email(EmailRequest emailRequest) {
        this.email = emailRequest.getEmail();
        this.tipoEmail = emailRequest.getTipoEmail();
    }

    public Email(Long idEmail, String email, TipoEmail tipoEmail, Pessoa pessoa) {
        this.idEmail = idEmail;
        this.email = email;
        this.tipoEmail = tipoEmail;
        this.pessoa = pessoa;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
