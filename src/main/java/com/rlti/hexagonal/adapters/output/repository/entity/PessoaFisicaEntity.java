package com.rlti.hexagonal.adapters.output.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rlti.hexagonal.application.core.domain.PessoaFisica;
import com.rlti.hexagonal.application.core.domain.Sexo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PESSOA_FISICA")
public class PessoaFisicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idPessoaFisica;

    @CPF @Column(unique = true, updatable = false, nullable = false)
    private String cpf;
    private String nomeAbreviado;
    private String nomeSocial;
    private LocalDate dataNascimento;
    private LocalDate dataObito;

    @Enumerated(EnumType.STRING)
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

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    @JsonIgnore
    private PessoaEntity pessoa;

    public PessoaFisicaEntity(PessoaFisica pessoaFisica) {
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
        this.indicadorFacta = pessoaFisica.getIndicadorFacta();
        this.indicadorPpe = pessoaFisica.getIndicadorPpe();
        this.especificacaoPpe = pessoaFisica.getEspecificacaoPpe();
    }
}
