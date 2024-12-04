package com.rlti.hexagonal.pessoa.adapters.output.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rlti.hexagonal.pessoa.application.core.domain.PessoaFisica;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.EstadoCivil;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.GrauDeInstrucao;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Sexo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "pessoaFisica", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Size(max = 3, message = "Uma pessoa pode ter no máximo 3 telefones.")
    private List<TelefoneEntity> telefones = new ArrayList<>();

    @OneToMany(mappedBy = "pessoaFisica", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<EmailEntity> emails = new ArrayList<>();;

    @ManyToOne
    @JoinColumn(name = "pais_id_pais")
    private PaisEntity nacionalidade;

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
                              PaisEntity nacionalidade,
                              OrientacaoSexualEntity orientacao,
                              IdentidadeGeneroEntity identidadeGenero,
                              ProfissaoEntity profissao) {
        this.cpf = pessoaFisica.getCpf();
        this.dataNascimento = pessoaFisica.getDataNascimento();
        this.sexo = pessoaFisica.getSexo();
        this.estadoCivil = pessoaFisica.getEstadoCivil();
        this.nomeSocial = pessoaFisica.getNomeSocial().toUpperCase();
        this.dataObito = pessoaFisica.getDataObito();
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
        addTelefones(pessoaFisica);
        addEmails(pessoaFisica);
        if (nacionalidade != null)
            this.nacionalidade = nacionalidade;
        if (orientacao != null)
            this.orientacaoSexual = orientacao;
        if (identidadeGenero != null)
            this.identidadeGenero = identidadeGenero;
        if (profissao != null)
            this.profissao = profissao;
    }

    private void addEmails(PessoaFisica pessoaFisica) {
        pessoaFisica.getEmails().forEach(email -> {
            EmailEntity emailEntity = new EmailEntity(email);
            emailEntity.setPessoaFisica(this);
            this.emails.add(emailEntity);
        });
    }

    private void addTelefones(PessoaFisica pessoaFisica) {
        pessoaFisica.getTelefones().forEach(telefone -> {
            TelefoneEntity telefoneEntity = new TelefoneEntity(telefone);
            telefoneEntity.setPessoaFisica(this);
            this.telefones.add(telefoneEntity);
        });
    }
}
