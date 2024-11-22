package com.rlti.hexagonal.application.core.domain;

import java.time.LocalDateTime;

public class Pessoa {
    private Long idPessoa;
    private String tipo;
    private String nome;
    private String resideExterior;
    private LocalDateTime createdAt;

    // Getters e Setters
    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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
}
