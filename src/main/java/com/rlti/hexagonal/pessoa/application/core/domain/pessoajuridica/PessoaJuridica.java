package com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica;

import com.rlti.hexagonal.pessoa.adapters.input.request.PessoaJuridicaRequest;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pessoa;

import java.time.LocalDate;

public class PessoaJuridica {
    private Long idPessoaJuridica;
    private String cnpj;
    private LocalDate dataAbertura;
    private String razaoSocial;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String situacao;

    private Pessoa pessoa;

    public PessoaJuridica() {
    }

    public PessoaJuridica(Long idPessoaJuridica, String cnpj, LocalDate dataAbertura, String razaoSocial, String nomeFantasia, String inscricaoEstadual, String inscricaoMunicipal, String situacao, Pessoa pessoa) {
        this.idPessoaJuridica = idPessoaJuridica;
        this.cnpj = cnpj;
        this.dataAbertura = dataAbertura;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.situacao = situacao;
        this.pessoa = pessoa;
    }

    public PessoaJuridica(PessoaJuridicaRequest pessoaJuridica) {
        this.cnpj = pessoaJuridica.getCnpj();
        this.razaoSocial = pessoaJuridica.getRazaoSocial();
    }

    public Long getIdPessoaJuridica() {
        return idPessoaJuridica;
    }

    public void setIdPessoaJuridica(Long idPessoaJuridica) {
        this.idPessoaJuridica = idPessoaJuridica;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
