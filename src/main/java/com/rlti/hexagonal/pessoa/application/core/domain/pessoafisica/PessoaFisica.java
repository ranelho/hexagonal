package com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica;

import com.rlti.hexagonal.pessoa.adapters.input.request.PessoaFisicaRequest;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Sexo;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pais;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pessoa;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data //todo - remove lombok
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
    private GrauInstrucao grauInstrucao;
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
        this.pais = new Pais(request.getIdPais());
        this.profissao = new Profissao(request.getProfissaoId());
        this.grauInstrucao = new GrauInstrucao(request.getIdGrauInstrucao());
        this.identidadeGenero = new IdentidadeGenero(request.getIdIdentidadeGenero());
        this.especificacaoIg = request.getEspecificacaoIg();
        this.orientacaoSexual = new OrientacaoSexual(request.getOrientacaoSexualId());
        this.especificacaoOs = request.getEspecificacaoOs();
        this.corRaca = new CorRaca(request.getCorRacaId());
        this.paisCrs = new Pais(request.getIdPaisCrs());
    }

}
