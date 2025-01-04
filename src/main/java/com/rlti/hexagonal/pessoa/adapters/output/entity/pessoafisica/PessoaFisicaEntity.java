package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica;

import com.rlti.hexagonal.pessoa.adapters.output.entity.banco.PaisEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.PessoaEntity;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Sexo;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.PessoaFisica;
import com.rlti.hexagonal.pessoa.config.EnumConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "TB_PESSOA_FISICA")
public class PessoaFisicaEntity  {

    @Id
    private Long idPessoaFisica;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ID_PESSOA_FISICA")
    private PessoaEntity pessoa;

    @CPF
    @Column(name= "NUM_CPF", unique = true, updatable = false, nullable = false)
    private String cpf;

    @Column(name = "NOM_SOCIAL")
    private String nomeSocial;

    @Column(name = "DAT_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "DAT_OBITO")
    private LocalDate dataObito;

    @Convert(converter = EnumConverter.class)
    @Enumerated(EnumType.STRING) @Column(name = "TP_SEXO")
    private Sexo sexo;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO_CIVIL")
    private EstadoCivilEntity estadoCivil;

    @Column(name = "NOM_PAI")
    private String nomePai;

    @Column(name = "NOM_MAE")
    private String nomeMae;

    @Column(name = "STS_RESIDE_EXTERIOR")
    private boolean resideExterior;

    @Column(name = "DSC_NATURALIDADE")
    private String naturalidade;

    @Column(name = "VAL_RENDA_MENSAL")
    private BigDecimal rendaMensal;

    @Column(name = "STS_INDICADOR_FATCA")
    private boolean indicadorFacta ;

    @Column(name = "DSC_MOTIVO_FATCA")
    private String motivoFacta;

    @Column(name = "STS_INDICADOR_CRS")
    private boolean indicadoCrs;

    @Column(name = "DSC_MOTIVO_CRS")
    private String motivoCrs;

    @ManyToOne
    @JoinColumn(name = "ID_PAIS_CRS")
    private PaisEntity paisCrs;

    @Column(name = "STS_INDICADOR_PPE")
    private boolean indicadorPpe ;

    @Column(name = "DSC_ESPECIFICACAO_PPE")
    private String especificacaoPpe;

    @ManyToOne
    @JoinColumn(name = "ID_GRAU_INSTRUCAO")
    private GrauInstrucaoEntity grauInstrucao;

    @ManyToOne
    @JoinColumn(name = "ID_PAIS")
    private PaisEntity pais;

    @ManyToOne
    @JoinColumn(name = "ID_IDENTIDADE_GENERO")
    private IdentidadeGeneroEntity identidadeGenero;

    @Column(name = "DSC_ESPECIFICACAO_IG")
    private String especificacaoIG;

    @ManyToOne
    @JoinColumn(name = "ID_ORIENTACAO_SEXUAL")
    private OrientacaoSexualEntity orientacaoSexual;

    @Column(name = "DSC_ESPECIFICACAO_OS")
    private String especificacaoOs;

    @ManyToOne
    @JoinColumn(name = "ID_COR_RACA")
    private CorRacaEntity corRacaEntity;

    @ManyToOne
    @JoinColumn(name = "ID_PROFISSAO")
    private ProfissaoEntity profissao;

    public PessoaFisicaEntity(PessoaFisica pessoaFisica,
                              PaisEntity pais,
                              OrientacaoSexualEntity orientacao,
                              IdentidadeGeneroEntity identidadeGenero,
                              ProfissaoEntity profissao, EstadoCivilEntity estadoCivil) {
        this.cpf = pessoaFisica.getCpf();
        this.dataNascimento = pessoaFisica.getDataNascimento();
        this.sexo = pessoaFisica.getSexo();
        this.estadoCivil = estadoCivil;
        this.nomeSocial = pessoaFisica.getNomeSocial().toUpperCase();
        this.dataObito = pessoaFisica.getDataObito();
        this.nomePai = pessoaFisica.getNomePai().toUpperCase();
        this.nomeMae = pessoaFisica.getNomeMae().toUpperCase();
        this.resideExterior = pessoaFisica.isResideExterior();
        this.naturalidade = pessoaFisica.getNaturalidade().toUpperCase();
        this.rendaMensal = pessoaFisica.getRendaMensal();
        this.indicadoCrs = pessoaFisica.isIndicadoCrs();
        this.indicadorFacta = pessoaFisica.isIndicadorFacta();
        this.indicadorPpe = pessoaFisica.isIndicadorPpe();
        this.especificacaoPpe = pessoaFisica.getEspecificacaoPpe();
        this.especificacaoIG = pessoaFisica.getEspecificacaoIg();
        this.especificacaoOs = pessoaFisica.getEspecificacaoOs();
        if (pais != null)
            this.pais = pais;
        if (orientacao != null)
            this.orientacaoSexual = orientacao;
        if (identidadeGenero != null)
            this.identidadeGenero = identidadeGenero;
        if (profissao != null)
            this.profissao = profissao;
    }

    public PessoaFisicaEntity(PessoaFisica pessoaFisica) {
        this.sexo = pessoaFisica.getSexo();
        this.estadoCivil = new EstadoCivilEntity(pessoaFisica.getEstadoCivil());
        this.nomeSocial = pessoaFisica.getNomeSocial().toUpperCase();
        this.dataObito = pessoaFisica.getDataObito();
        this.resideExterior = pessoaFisica.isResideExterior();
        this.nomePai = pessoaFisica.getNomePai().toUpperCase();
        this.nomeMae = pessoaFisica.getNomeMae().toUpperCase();
        this.resideExterior = pessoaFisica.isResideExterior();
        this.naturalidade = pessoaFisica.getNaturalidade().toUpperCase();
        this.rendaMensal = pessoaFisica.getRendaMensal();
        this.indicadoCrs = pessoaFisica.isIndicadoCrs();
        this.indicadorFacta = pessoaFisica.isIndicadorFacta();
        this.indicadorPpe = pessoaFisica.isIndicadorPpe();
        this.especificacaoPpe = pessoaFisica.getEspecificacaoPpe();
        this.especificacaoIG = pessoaFisica.getEspecificacaoIg();
        this.especificacaoOs = pessoaFisica.getEspecificacaoOs();
    }

    public void update(PessoaFisica pessoa) {
        this.nomeSocial = pessoa.getNomeSocial().toUpperCase();
        this.resideExterior = pessoa.isResideExterior();
        this.dataObito = pessoa.getDataObito();
        this.sexo = pessoa.getSexo();
        this.rendaMensal = pessoa.getRendaMensal();
        this.indicadorFacta = pessoa.isIndicadorFacta();
        this.indicadoCrs = pessoa.isIndicadoCrs();
        this.indicadorPpe = pessoa.isIndicadorPpe();
        this.especificacaoPpe = pessoa.getEspecificacaoPpe();
    }

}
