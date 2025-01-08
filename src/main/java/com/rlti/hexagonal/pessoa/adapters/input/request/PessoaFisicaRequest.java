package com.rlti.hexagonal.pessoa.adapters.input.request;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.Sexo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class PessoaFisicaRequest extends PessoaRequest {
    @NotNull
    @CPF(message = "CPF inválido")
    @Pattern(regexp = "^\\d{11}$", message = "CPF inválido")
    private String cpf;

    private String nomeSocial;
    private boolean resideExterior;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    private LocalDate dataObito;

    @NotNull
    private Sexo sexo;

    private Long idEstadoCivil;
    private String nomePai;
    private String nomeMae;
    private String estrangeiro;
    private String naturalidade;
    private BigDecimal rendaMensal;
    private boolean indicadorFacta;
    private boolean indicadoCrs;
    private boolean indicadorPpe;
    private String especificacaoPpe;

    @NotNull
    private Long idPais;
    private Long idPaisCrs;

    @NotNull(message = "profissaoId não pode ser nulo")
    private Long profissaoId;

    @NotNull(message = "idGrauInstrucao não pode ser nulo")
    private Long idGrauInstrucao;

    @NotNull(message = "idIdentidadeGenero não pode ser nulo")
    private Long idIdentidadeGenero;

    private String especificacaoIg;

    @NotNull(message = "orientacaoSexualId não pode ser nulo")
    private Long orientacaoSexualId;

    private String especificacaoOs;

    @NotNull(message = "corRacaId não pode ser nulo")
    private Long corRacaId;
}
