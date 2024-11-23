package com.rlti.hexagonal.application.core.domain;

import com.rlti.hexagonal.adapters.input.controller.request.PessoaFisicaRequest;

import java.time.LocalDate;
import java.util.List;

public class PessoaFisica {
    private Long id;
    private String cpf;
    private LocalDate dataNascimento;
    private List<Telefone> telefones;

    public PessoaFisica() {
    }

    public PessoaFisica(Long id, String cpf, LocalDate dataNascimento) {
        this.id = id;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public PessoaFisica(PessoaFisicaRequest pessoaFisica) {
        this.cpf = pessoaFisica.getCpf();
        this.dataNascimento = pessoaFisica.getDataNascimento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
