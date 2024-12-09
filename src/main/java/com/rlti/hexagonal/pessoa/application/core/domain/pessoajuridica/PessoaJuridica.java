package com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica;

import com.rlti.hexagonal.pessoa.adapters.input.request.PessoaJuridicaRequest;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pessoa;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PessoaJuridica {
    private Long idPessoaJuridica;
    private String cnpj;
    private LocalDate dataAbertura;
    private String razaoSocial;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String situacao;

    private String cnaePrincipal;
    private Pessoa pessoa;
    private List<Cnae> cnaes;


    public PessoaJuridica(PessoaJuridicaRequest pessoaJuridica) {
        this.cnpj = pessoaJuridica.getCnpj();
        this.razaoSocial = pessoaJuridica.getRazaoSocial();
        this.nomeFantasia = pessoaJuridica.getNomeFantasia();
        this.inscricaoEstadual = pessoaJuridica.getInscricaoEstadual();
        this.inscricaoMunicipal = pessoaJuridica.getInscricaoMunicipal();
        this.situacao = pessoaJuridica.getSituacao();
        this.cnaePrincipal = pessoaJuridica.getCnaePrincipal();
        this.cnaes = pessoaJuridica.getCnaes().stream()
                .map(relacao -> new Cnae(relacao.getSubclasse()))
                .collect(Collectors.toList());
    }

    public PessoaJuridica() {
    }

    public PessoaJuridica(Long idPessoaJuridica, String cnpj, LocalDate dataAbertura, String razaoSocial, String nomeFantasia, String inscricaoEstadual, String inscricaoMunicipal, String situacao, String cnaePrincipal, Pessoa pessoa, List<Cnae> cnaes) {
        this.idPessoaJuridica = idPessoaJuridica;
        this.cnpj = cnpj;
        this.dataAbertura = dataAbertura;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.situacao = situacao;
        this.cnaePrincipal = cnaePrincipal;
        this.pessoa = pessoa;
        this.cnaes = cnaes;
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

    public String getSubclasse() {
        return cnaePrincipal;
    }

    public void setSubclasse(String subclasse) {
        this.cnaePrincipal = subclasse;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Cnae> getCnaes() {
        return cnaes;
    }

    public void setCnaes(List<Cnae> cnaes) {
        this.cnaes = cnaes;
    }
}
