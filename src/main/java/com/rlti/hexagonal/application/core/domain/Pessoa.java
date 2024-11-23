package com.rlti.hexagonal.application.core.domain;

import com.rlti.hexagonal.adapters.input.controller.request.PessoaRequest;
import com.rlti.hexagonal.adapters.output.repository.entity.Tipo;

import java.time.LocalDateTime;

public class Pessoa {
    private Long idPessoa;
    private Tipo tipo;
    private String nome;
    private String resideExterior;
    private LocalDateTime createdAt;
    private PessoaFisica pessoaFisica;
    private PessoaJuridica pessoaJuridica;

    public Pessoa() {
    }

    public Pessoa(PessoaRequest request) {
        this.tipo = request.getTipo();
        this.nome = request.getNome();
        this.resideExterior = request.getResideExterior();
        this.pessoaFisica = new PessoaFisica(request.getPessoaFisica());
        this.pessoaJuridica = new PessoaJuridica(request.getPessoaJuridica());
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResideExterior() {
        return resideExterior;
    }

    public void setResideExterior(String resideExterior) {
        this.resideExterior = resideExterior;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public Pessoa(Long idPessoa, Tipo tipo, String nome, String resideExterior, LocalDateTime createdAt, PessoaFisica pessoaFisica, PessoaJuridica pessoaJuridica) {
        this.idPessoa = idPessoa;
        this.tipo = tipo;
        this.nome = nome;
        this.resideExterior = resideExterior;
        this.createdAt = createdAt;
        this.pessoaFisica = pessoaFisica;
        this.pessoaJuridica = pessoaJuridica;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }
}
