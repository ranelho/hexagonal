package com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica;

import com.rlti.hexagonal.pessoa.adapters.input.request.PessoaFisicaRequest;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.EstadoCivil;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.GrauDeInstrucao;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Sexo;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pais;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pessoa;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PessoaFisica {
    private Long idPessoaFisica;
    private String cpf;
    private String nomeSocial;
    private boolean resideExterior;
    private LocalDate dataNascimento;
    private LocalDate dataObito;
    private Sexo sexo;
    private EstadoCivil estadoCivil;
    private String nomePai;
    private String nomeMae;
    private String estrangeiro;
    private String naturalidade;
    private BigDecimal rendaMensal;
    private boolean indicadorFacta;
    private boolean indicadoCrs;
    private boolean indicadorPpe;
    private String especificacaoPpe;

    private Pessoa pessoa;
    private Pais pais;
    private Pais paisCrs;

    private Profissao profissao;
    private GrauDeInstrucao grauDeInstrucao;
    private IdentidadeGenero identidadeGenero;
    private String especificacaoIg;
    private OrientacaoSexual orientacaoSexual;
    private String especificacaoOs;
    private CorRaca corRaca;


    public PessoaFisica() {
    }

    public PessoaFisica(PessoaFisicaRequest request) {
        this.cpf = request.getCpf();
        this.nomeSocial = request.getNomeSocial();
        this.dataNascimento = request.getDataNascimento();
        this.resideExterior = request.isResideExterior();
        this.dataObito = request.getDataObito();
        this.sexo = request.getSexo();
        this.estadoCivil = new EstadoCivil(request.getIdEstadoCivil());
        this.nomePai = request.getNomePai();
        this.nomeMae = request.getNomeMae();
        this.estrangeiro = request.getEstrangeiro();
        this.naturalidade = request.getNaturalidade();
        this.rendaMensal = request.getRendaMensal();
        this.indicadorFacta = request.isIndicadorFacta();
        this.indicadoCrs = request.isIndicadoCrs();
        this.indicadorPpe = request.isIndicadorPpe();
        this.especificacaoPpe = request.getEspecificacaoPpe();
        this.pais = new Pais(request.getPaisId());
        this.profissao = new Profissao(request.getProfissaoId());
        this.grauDeInstrucao = request.getGrauDeInstrucao();
        this.identidadeGenero = new IdentidadeGenero(request.getIdentidadeDeGeneroId());
        this.especificacaoIg = request.getEspecificacaoIg();
        this.orientacaoSexual = new OrientacaoSexual(request.getOrientacaoSexualId());
        this.especificacaoOs = request.getEspecificacaoOs();
        this.corRaca = new CorRaca(request.getCorRacaId());
        this.paisCrs = new Pais(request.getIdPaisCrs());
    }

    public PessoaFisica(Long idPessoaFisica, String cpf, String nomeSocial, boolean resideExterior, LocalDate dataNascimento, LocalDate dataObito, Sexo sexo, EstadoCivil estadoCivil, String nomePai, String nomeMae, String estrangeiro, String naturalidade, BigDecimal rendaMensal, boolean indicadorFacta, boolean indicadoCrs, boolean indicadorPpe, String especificacaoPpe, Pessoa pessoa, Pais pais, Pais paisCrs, Profissao profissao, GrauDeInstrucao grauDeInstrucao, IdentidadeGenero identidadeGenero, String especificacaoIg, OrientacaoSexual orientacaoSexual, String especificacaoOs, CorRaca corRaca) {
        this.idPessoaFisica = idPessoaFisica;
        this.cpf = cpf;
        this.nomeSocial = nomeSocial;
        this.resideExterior = resideExterior;
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
        this.pessoa = pessoa;
        this.pais = pais;
        this.paisCrs = paisCrs;
        this.profissao = profissao;
        this.grauDeInstrucao = grauDeInstrucao;
        this.identidadeGenero = identidadeGenero;
        this.especificacaoIg = especificacaoIg;
        this.orientacaoSexual = orientacaoSexual;
        this.especificacaoOs = especificacaoOs;
        this.corRaca = corRaca;
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

    public boolean isResideExterior() {
        return resideExterior;
    }

    public void setResideExterior(boolean resideExterior) {
        this.resideExterior = resideExterior;
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

    public boolean isIndicadorFacta() {
        return indicadorFacta;
    }

    public void setIndicadorFacta(boolean indicadorFacta) {
        this.indicadorFacta = indicadorFacta;
    }

    public boolean isIndicadoCrs() {
        return indicadoCrs;
    }

    public void setIndicadoCrs(boolean indicadoCrs) {
        this.indicadoCrs = indicadoCrs;
    }

    public boolean isIndicadorPpe() {
        return indicadorPpe;
    }

    public void setIndicadorPpe(boolean indicadorPpe) {
        this.indicadorPpe = indicadorPpe;
    }

    public String getEspecificacaoPpe() {
        return especificacaoPpe;
    }

    public void setEspecificacaoPpe(String especificacaoPpe) {
        this.especificacaoPpe = especificacaoPpe;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Pais getPaisCrs() {
        return paisCrs;
    }

    public void setPaisCrs(Pais paisCrs) {
        this.paisCrs = paisCrs;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public GrauDeInstrucao getGrauDeInstrucao() {
        return grauDeInstrucao;
    }

    public void setGrauDeInstrucao(GrauDeInstrucao grauDeInstrucao) {
        this.grauDeInstrucao = grauDeInstrucao;
    }

    public IdentidadeGenero getIdentidadeGenero() {
        return identidadeGenero;
    }

    public void setIdentidadeGenero(IdentidadeGenero identidadeGenero) {
        this.identidadeGenero = identidadeGenero;
    }

    public String getEspecificacaoIg() {
        return especificacaoIg;
    }

    public void setEspecificacaoIg(String especificacaoIg) {
        this.especificacaoIg = especificacaoIg;
    }

    public OrientacaoSexual getOrientacaoSexual() {
        return orientacaoSexual;
    }

    public void setOrientacaoSexual(OrientacaoSexual orientacaoSexual) {
        this.orientacaoSexual = orientacaoSexual;
    }

    public String getEspecificacaoOs() {
        return especificacaoOs;
    }

    public void setEspecificacaoOs(String especificacaoOs) {
        this.especificacaoOs = especificacaoOs;
    }

    public CorRaca getCorRaca() {
        return corRaca;
    }

    public void setCorRaca(CorRaca corRaca) {
        this.corRaca = corRaca;
    }
}
