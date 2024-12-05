package com.rlti.hexagonal.pessoa.application.core.domain;

import com.rlti.hexagonal.pessoa.adapters.input.controller.request.TelefoneRequest;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoTelefone;

import java.util.List;

public class Telefone {
    private Long id;
    private TipoTelefone tipoTelefone;
    private String ddi;
    private String ddd;
    private String numeroTelefone;
    private Pessoa pessoa;

    public Telefone(TipoTelefone tipoTelefone, String ddi,String ddd,String numeroTelefone, Pessoa pessoa) {
        this.tipoTelefone = tipoTelefone;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numeroTelefone = numeroTelefone;
        this.pessoa = pessoa;
    }

    public Telefone(TelefoneRequest telefoneRequest) {
        this.tipoTelefone = telefoneRequest.getTipoTelefone();
        this.ddi = telefoneRequest.getDdi();
        this.ddd = telefoneRequest.getDdd();
        this.numeroTelefone = telefoneRequest.getNumeroTelefone();
    }

    public static List<Telefone> converte(List<TelefoneRequest> telefones, Pessoa pessoa) {
        return telefones.stream().map(telefone -> new Telefone(telefone.getTipoTelefone(), telefone.getDdi(), telefone.getDdd(), telefone.getNumeroTelefone(), pessoa)).toList();
    }

    public void update(TelefoneRequest request) {
        this.tipoTelefone = request.getTipoTelefone();
        this.ddi = request.getDdi();
        this.ddd = request.getDdd();
        this.numeroTelefone = request.getNumeroTelefone();
    }

    public Telefone(Long id, TipoTelefone tipoTelefone, String ddi, String ddd, String numeroTelefone, Pessoa pessoa) {
        this.id = id;
        this.tipoTelefone = tipoTelefone;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numeroTelefone = numeroTelefone;
        this.pessoa = pessoa;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
