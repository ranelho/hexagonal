package com.rlti.hexagonal.application.core.domain;

import com.rlti.hexagonal.adapters.input.controller.request.PessoaFisicaRequest;
import com.rlti.hexagonal.application.core.domain.enums.EstadoCivil;
import com.rlti.hexagonal.application.core.domain.enums.GrauDeInstrucao;
import com.rlti.hexagonal.application.core.domain.enums.Sexo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PessoaFisica {
    private Long idPessoaFisica;
    private String cpf;
    private String nomeSocial;
    private LocalDate dataNascimento;
    private LocalDate dataObito;
    private Sexo sexo;
    private EstadoCivil estadoCivil;
    private String nomePai;
    private String nomeMae;
    private String estrangeiro;
    private String naturalidade;
    private BigDecimal rendaMensal;
    private String indicadorFacta;
    private String indicadoCrs;
    private String indicadorPpe;
    private String especificacaoPpe;

    private List<Telefone> telefones;
    private List<Email> emails;

    private Pais pais;

    private Long paisId;
    private Long profissaoId;
    private GrauDeInstrucao grauDeInstrucao;
    private Long identidadeDeGeneroId;
    private String especificacaoIg;
    private Long orientacaoSexualId;
    private String especificacaoOs;
    private Long corRacaId;

    private Pessoa pessoa;

    public PessoaFisica() {
    }

    public PessoaFisica(PessoaFisicaRequest request) {
        this.cpf = request.getCpf();
        this.nomeSocial = request.getNomeSocial();
        this.dataNascimento = request.getDataNascimento();
        this.dataObito = request.getDataObito();
        this.sexo = request.getSexo();
        this.estadoCivil = request.getEstadoCivil();
        this.nomePai = request.getNomePai();
        this.nomeMae = request.getNomeMae();
        this.estrangeiro = request.getEstrangeiro();
        this.naturalidade = request.getNaturalidade();
        this.rendaMensal = request.getRendaMensal();
        this.indicadorFacta = request.getIndicadorFacta();
        this.indicadoCrs = request.getIndicadoCrs();
        this.indicadorPpe = request.getIndicadorPpe();
        this.especificacaoPpe = request.getEspecificacaoPpe();
        this.telefones = new Telefone().converteList(request.getTelefones());
        this.paisId = request.getPaisId();
        this.profissaoId = request.getProfissaoId();
        this.grauDeInstrucao = request.getGrauDeInstrucao();
        this.identidadeDeGeneroId = request.getIdentidadeDeGeneroId();
        this.especificacaoIg = request.getEspecificacaoIg();
        this.orientacaoSexualId = request.getOrientacaoSexualId();
        this.especificacaoOs = request.getEspecificacaoOs();
        this.corRacaId = request.getCorRacaId();
    }

    public PessoaFisica(Long idPessoaFisica, String cpf, String nomeSocial, LocalDate dataNascimento, LocalDate dataObito, Sexo sexo, EstadoCivil estadoCivil, String nomePai, String nomeMae, String estrangeiro, String naturalidade, BigDecimal rendaMensal, String indicadorFacta, String indicadoCrs, String indicadorPpe, String especificacaoPpe, List<Telefone> telefones, List<Email> emails, Pais pais, Long paisId, Long profissaoId, GrauDeInstrucao grauDeInstrucao, Long identidadeDeGeneroId, String especificacaoIg, Long orientacaoSexualId, String especificacaoOs, Long corRacaId, Pessoa pessoa) {
        this.idPessoaFisica = idPessoaFisica;
        this.cpf = cpf;
        this.nomeSocial = nomeSocial;
        this.dataNascimento = dataNascimento;
        this.dataObito = dataObito;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.estrangeiro = estrangeiro;
        this.naturalidade = naturalidade;
        this.rendaMensal = rendaMensal;
        this.indicadorFacta = indicadorFacta;
        this.indicadoCrs = indicadoCrs;
        this.indicadorPpe = indicadorPpe;
        this.especificacaoPpe = especificacaoPpe;
        this.telefones = telefones;
        this.emails = emails;
        this.pais = pais;
        this.paisId = paisId;
        this.profissaoId = profissaoId;
        this.grauDeInstrucao = grauDeInstrucao;
        this.identidadeDeGeneroId = identidadeDeGeneroId;
        this.especificacaoIg = especificacaoIg;
        this.orientacaoSexualId = orientacaoSexualId;
        this.especificacaoOs = especificacaoOs;
        this.corRacaId = corRacaId;
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

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
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

    public String getIndicadoCrs() {
        return indicadoCrs;
    }

    public void setIndicadoCrs(String indicadoCrs) {
        this.indicadoCrs = indicadoCrs;
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

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Long getPaisId() {
        return paisId;
    }

    public void setPaisId(Long paisId) {
        this.paisId = paisId;
    }

    public Long getProfissaoId() {
        return profissaoId;
    }

    public void setProfissaoId(Long profissaoId) {
        this.profissaoId = profissaoId;
    }

    public GrauDeInstrucao getGrauDeInstrucao() {
        return grauDeInstrucao;
    }

    public void setGrauDeInstrucao(GrauDeInstrucao grauDeInstrucao) {
        this.grauDeInstrucao = grauDeInstrucao;
    }

    public Long getIdentidadeDeGeneroId() {
        return identidadeDeGeneroId;
    }

    public void setIdentidadeDeGeneroId(Long identidadeDeGeneroId) {
        this.identidadeDeGeneroId = identidadeDeGeneroId;
    }

    public String getEspecificacaoIg() {
        return especificacaoIg;
    }

    public void setEspecificacaoIg(String especificacaoIg) {
        this.especificacaoIg = especificacaoIg;
    }

    public Long getOrientacaoSexualId() {
        return orientacaoSexualId;
    }

    public void setOrientacaoSexualId(Long orientacaoSexualId) {
        this.orientacaoSexualId = orientacaoSexualId;
    }

    public String getEspecificacaoOs() {
        return especificacaoOs;
    }

    public void setEspecificacaoOs(String especificacaoOs) {
        this.especificacaoOs = especificacaoOs;
    }

    public Long getCorRacaId() {
        return corRacaId;
    }

    public void setCorRacaId(Long corRacaId) {
        this.corRacaId = corRacaId;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
