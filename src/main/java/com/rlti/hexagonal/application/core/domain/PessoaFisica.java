package com.rlti.hexagonal.application.core.domain;

import com.rlti.hexagonal.adapters.input.controller.request.PessoaFisicaRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PessoaFisica {
    private Long idPessoaFisica;
    private String cpf;
    private String nomeAbreviado;
    private String nomeSocial;
    private LocalDate dataNascimento;
    private LocalDate dataObito;
    private Sexo sexo;
    private String nomePai;
    private String nomeMae;
    private String pep;
    private String estrangeiro;
    private String naturalidade;
    private BigDecimal rendaMensal;
    private String indicadorFacta;
    private String indicadorPpe;
    private String especificacaoPpe;
    private List<Telefone> telefones;

    private Pessoa pessoa;

    public PessoaFisica() {
    }

    public PessoaFisica(PessoaFisicaRequest pessoaFisica) {
        this.cpf = pessoaFisica.getCpf();
        this.dataNascimento = pessoaFisica.getDataNascimento();
        this.sexo = pessoaFisica.getSexo();
        this.nomeAbreviado = pessoaFisica.getNomeAbreviado().toUpperCase();
        this.nomeSocial = pessoaFisica.getNomeSocial().toUpperCase();
        this.dataObito = pessoaFisica.getDataObito();
        this.nomePai = pessoaFisica.getNomePai().toUpperCase();
        this.nomeMae = pessoaFisica.getNomeMae().toUpperCase();
        this.pep = pessoaFisica.getPep();
        this.estrangeiro = pessoaFisica.getEstrangeiro().toUpperCase();
        this.naturalidade = pessoaFisica.getNaturalidade().toUpperCase();
        this.rendaMensal = pessoaFisica.getRendaMensal();
        this.indicadorFacta = pessoaFisica.getIndicadorFacta().toUpperCase();
        this.indicadorPpe = pessoaFisica.getIndicadorPpe().toUpperCase();
        this.especificacaoPpe = pessoaFisica.getEspecificacaoPpe().toUpperCase();
    }

    public PessoaFisica(Long idPessoaFisica, String cpf, String nomeAbreviado, String nomeSocial, LocalDate dataNascimento, LocalDate dataObito, Sexo sexo, String nomePai, String nomeMae, String pep, String estrangeiro, String naturalidade, BigDecimal rendaMensal, String indicadorFacta, String indicadorPpe, String especificacaoPpe, List<Telefone> telefones, Pessoa pessoa) {
        this.idPessoaFisica = idPessoaFisica;
        this.cpf = cpf;
        this.nomeAbreviado = nomeAbreviado;
        this.nomeSocial = nomeSocial;
        this.dataNascimento = dataNascimento;
        this.dataObito = dataObito;
        this.sexo = sexo;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.pep = pep;
        this.estrangeiro = estrangeiro;
        this.naturalidade = naturalidade;
        this.rendaMensal = rendaMensal;
        this.indicadorFacta = indicadorFacta;
        this.indicadorPpe = indicadorPpe;
        this.especificacaoPpe = especificacaoPpe;
        this.telefones = telefones;
        this.pessoa = pessoa;
    }

    public Long getIdPessoaFisica() {
        return idPessoaFisica;
    }

    public void setIdPessoaFisica(Long idPessoaFisica) {
        this.idPessoaFisica = idPessoaFisica;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeAbreviado() {
        return nomeAbreviado;
    }

    public void setNomeAbreviado(String nomeAbreviado) {
        this.nomeAbreviado = nomeAbreviado;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataObito() {
        return dataObito;
    }

    public void setDataObito(LocalDate dataObito) {
        this.dataObito = dataObito;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getPep() {
        return pep;
    }

    public void setPep(String pep) {
        this.pep = pep;
    }

    public String getEstrangeiro() {
        return estrangeiro;
    }

    public void setEstrangeiro(String estrangeiro) {
        this.estrangeiro = estrangeiro;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public BigDecimal getRendaMensal() {
        return rendaMensal;
    }

    public void setRendaMensal(BigDecimal rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public String getIndicadorFacta() {
        return indicadorFacta;
    }

    public void setIndicadorFacta(String indicadorFacta) {
        this.indicadorFacta = indicadorFacta;
    }

    public String getIndicadorPpe() {
        return indicadorPpe;
    }

    public void setIndicadorPpe(String indicadorPpe) {
        this.indicadorPpe = indicadorPpe;
    }

    public String getEspecificacaoPpe() {
        return especificacaoPpe;
    }

    public void setEspecificacaoPpe(String especificacaoPpe) {
        this.especificacaoPpe = especificacaoPpe;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
