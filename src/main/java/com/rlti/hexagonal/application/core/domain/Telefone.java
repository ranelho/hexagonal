package com.rlti.hexagonal.application.core.domain;

import com.rlti.hexagonal.adapters.input.controller.request.TelefoneRequest;
import com.rlti.hexagonal.adapters.output.repository.entity.TipoTelefone;

public class Telefone {
    private Long id;
    private TipoTelefone tipoTelefone;
    private String ddi;
    private String ddd;
    private String numeroTelefone;
    private PessoaFisica pessoaFisica;

    public Telefone(){
    }

    public Telefone(TelefoneRequest request, PessoaFisica pessoaFisica) {
        this.tipoTelefone = request.getTipoTelefone();
        this.ddi = request.getDdi();
        this.ddd = request.getDdd();
        this.numeroTelefone = request.getNumeroTelefone();
        this.pessoaFisica =  pessoaFisica;
    }
    public Telefone(Long id, TipoTelefone tipoTelefone, String ddi, String ddd, String numeroTelefone, PessoaFisica pessoaFisica) {
        this.id = id;
        this.tipoTelefone = tipoTelefone;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numeroTelefone = numeroTelefone;
        this.pessoaFisica = pessoaFisica;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }
}
