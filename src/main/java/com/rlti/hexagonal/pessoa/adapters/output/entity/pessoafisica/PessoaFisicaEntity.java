package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica;

import com.rlti.hexagonal.pessoa.adapters.output.entity.banco.PaisEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.PessoaEntity;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.PessoaFisica;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.EstadoCivil;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.GrauDeInstrucao;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Sexo;
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
@Entity(name = "PESSOA_FISICA")
public class PessoaFisicaEntity  {

    @Id
    @Column(name = "id_pessoa")
    private Long idPessoa;

    @OneToOne
    @MapsId
    @JoinColumn(name = "idPessoa")
    private PessoaEntity pessoa;

    @CPF @Column(unique = true, updatable = false, nullable = false)
    private String cpf;
    private String nomeSocial;
    private LocalDate dataNascimento;
    private LocalDate dataObito;
    private String resideExterior;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;
    private String nomePai;
    private String nomeMae;
    private String estrangeiro ;
    private String naturalidade;
    private BigDecimal rendaMensal;
    private String indicadorFacta ;
    private String indicadoCrs;
    private String indicadorPpe ;
    private String especificacaoPpe;
    private String especificacaoIg;
    private String especificacaoOs;

    @Enumerated(EnumType.STRING)
    private GrauDeInstrucao grauDeInstrucao;

/*    @OneToMany(mappedBy = "pessoaFisica", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Size(max = 3, message = "Uma pessoa pode ter no máximo 3 telefones.")
    private List<TelefoneEntity> telefones = new ArrayList<>();*/

/*    @OneToMany(mappedBy = "pessoaFisica", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<EmailEntity> emails = new ArrayList<>();*/

    @ManyToOne
    @JoinColumn(name = "ID_PAIS")
    private PaisEntity pais;

    @ManyToOne
    @JoinColumn(name = "identidade_genero_id")
    private IdentidadeGeneroEntity identidadeGenero;

    @ManyToOne
    @JoinColumn(name = "orientacao_sexual_id")
    private OrientacaoSexualEntity orientacaoSexual;

    @ManyToOne
    @JoinColumn(name = "cor_raca_id")
    private CorRacaEntity corRacaEntity;

    @ManyToOne
    @JoinColumn(name = "profissao_id")
    private ProfissaoEntity profissao;

    public PessoaFisicaEntity(PessoaFisica pessoaFisica,
                              PaisEntity pais,
                              OrientacaoSexualEntity orientacao,
                              IdentidadeGeneroEntity identidadeGenero,
                              ProfissaoEntity profissao) {
        this.cpf = pessoaFisica.getCpf();
        this.dataNascimento = pessoaFisica.getDataNascimento();
        this.sexo = pessoaFisica.getSexo();
        this.estadoCivil = pessoaFisica.getEstadoCivil();
        this.nomeSocial = pessoaFisica.getNomeSocial().toUpperCase();
        this.dataObito = pessoaFisica.getDataObito();
        this.resideExterior = pessoaFisica.getResideExterior();
        this.nomePai = pessoaFisica.getNomePai().toUpperCase();
        this.nomeMae = pessoaFisica.getNomeMae().toUpperCase();
        this.estrangeiro = pessoaFisica.getEstrangeiro().isEmpty() ? "N" : pessoaFisica.getEstrangeiro().toUpperCase();
        this.naturalidade = pessoaFisica.getNaturalidade().toUpperCase();
        this.rendaMensal = pessoaFisica.getRendaMensal();
        this.indicadoCrs = pessoaFisica.getIndicadoCrs().isEmpty() ? "N" : pessoaFisica.getIndicadoCrs();
        this.indicadorFacta = pessoaFisica.getIndicadorFacta().isEmpty() ? "N" : pessoaFisica.getIndicadorFacta();
        this.indicadorPpe = pessoaFisica.getIndicadorPpe().isEmpty() ? "N" : pessoaFisica.getIndicadorPpe();
        this.especificacaoPpe = pessoaFisica.getEspecificacaoPpe();
        this.especificacaoIg = pessoaFisica.getEspecificacaoIg();
        this.especificacaoOs = pessoaFisica.getEspecificacaoOs();
        this.grauDeInstrucao = pessoaFisica.getGrauDeInstrucao();
       //addTelefones(pessoaFisica);
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
        this.estadoCivil = pessoaFisica.getEstadoCivil();
        this.nomeSocial = pessoaFisica.getNomeSocial().toUpperCase();
        this.dataObito = pessoaFisica.getDataObito();
        this.resideExterior = pessoaFisica.getResideExterior();
        this.nomePai = pessoaFisica.getNomePai().toUpperCase();
        this.nomeMae = pessoaFisica.getNomeMae().toUpperCase();
        this.estrangeiro = pessoaFisica.getEstrangeiro().isEmpty() ? "N" : pessoaFisica.getEstrangeiro().toUpperCase();
        this.naturalidade = pessoaFisica.getNaturalidade().toUpperCase();
        this.rendaMensal = pessoaFisica.getRendaMensal();
        this.indicadoCrs = pessoaFisica.getIndicadoCrs().isEmpty() ? "N" : pessoaFisica.getIndicadoCrs();
        this.indicadorFacta = pessoaFisica.getIndicadorFacta().isEmpty() ? "N" : pessoaFisica.getIndicadorFacta();
        this.indicadorPpe = pessoaFisica.getIndicadorPpe().isEmpty() ? "N" : pessoaFisica.getIndicadorPpe();
        this.especificacaoPpe = pessoaFisica.getEspecificacaoPpe();
        this.especificacaoIg = pessoaFisica.getEspecificacaoIg();
        this.especificacaoOs = pessoaFisica.getEspecificacaoOs();
        this.grauDeInstrucao = pessoaFisica.getGrauDeInstrucao();
    }

    public void update(PessoaFisica pessoa) {
        this.nomeSocial = pessoa.getNomeSocial().toUpperCase();
        this.resideExterior = pessoa.getResideExterior();
        this.dataObito = pessoa.getDataObito();
        this.sexo = pessoa.getSexo();
        this.estadoCivil = pessoa.getEstadoCivil();
        this.rendaMensal = pessoa.getRendaMensal();
        this.indicadorFacta = pessoa.getIndicadorFacta().isEmpty() ? "N" : pessoa.getIndicadorFacta();
        this.indicadoCrs = pessoa.getIndicadoCrs().isEmpty() ? "N" : pessoa.getIndicadoCrs();
        this.indicadorPpe = pessoa.getIndicadorPpe().isEmpty() ? "N" : pessoa.getIndicadorPpe();
        this.especificacaoPpe = pessoa.getEspecificacaoPpe();
        this.grauDeInstrucao = pessoa.getGrauDeInstrucao();
    }

}
