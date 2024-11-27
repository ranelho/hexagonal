package com.rlti.hexagonal.application.core.domain;

import com.rlti.hexagonal.adapters.input.controller.request.TelefoneRequest;
import com.rlti.hexagonal.application.core.domain.enums.TipoTelefone;

import java.util.List;

public class Telefone {
    private Long id;
    private TipoTelefone tipoTelefone;
    private String ddi;
    private String ddd;
    private String numeroTelefone;
    private PessoaFisica pessoaFisica;
    public Telefone() {
        this.id = id;
        this.tipoTelefone = tipoTelefone;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numeroTelefone = numeroTelefone;
        this.pessoaFisica = pessoaFisica;
    }

    public Telefone(TipoTelefone tipoTelefone, String ddi,String ddd,String numeroTelefone, PessoaFisica pessoa) {
        this.tipoTelefone = tipoTelefone;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numeroTelefone = numeroTelefone;
        this.pessoaFisica = pessoa;
    }

    public static List<Telefone> converte(List<TelefoneRequest> telefones, PessoaFisica pessoa) {
        return telefones.stream().map(telefone -> new Telefone(telefone.getTipoTelefone(), telefone.getDdi(), telefone.getDdd(), telefone.getNumeroTelefone(), pessoa)).toList();
    }

    public List<Telefone> converteList(List<TelefoneRequest> telefones) {
        return telefones.stream().map(telefone -> new Telefone(telefone.getTipoTelefone(), telefone.getDdi(), telefone.getDdd(), telefone.getNumeroTelefone(), pessoaFisica)).toList();
    }

    public void update(TelefoneRequest request) {
        this.tipoTelefone = request.getTipoTelefone();
        this.ddi = request.getDdi();
        this.ddd = request.getDdd();
        this.numeroTelefone = request.getNumeroTelefone();
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

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }


}
