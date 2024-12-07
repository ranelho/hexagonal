package com.rlti.hexagonal.pessoa.application.core.domain.pessoa;

import java.util.List;

public class Banco {
    private Long idBanco;
    private String nome;
    private String codigo;
    private String cnpj;
    private String inscricaoEstadual;

    List<Agencia> agencias;

    public Banco() {
    }
    public Banco(Long idBanco, String nome, String codigo, String cnpj, String inscricaoEstadual, List<Agencia> agencias) {
        this.idBanco = idBanco;
        this.nome = nome;
        this.codigo = codigo;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.agencias = agencias;
    }

    public Long getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Long idBanco) {
        this.idBanco = idBanco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public List<Agencia> getAgencias() {
        return agencias;
    }

    public void setAgencias(List<Agencia> agencias) {
        this.agencias = agencias;
    }
}
