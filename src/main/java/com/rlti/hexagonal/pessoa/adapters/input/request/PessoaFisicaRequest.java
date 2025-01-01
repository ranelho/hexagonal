package com.rlti.hexagonal.pessoa.adapters.input.request;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.EstadoCivil;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.GrauDeInstrucao;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Sexo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class PessoaFisicaRequest extends PessoaRequest {
    @NotNull @CPF(message = "CPF inválido")
  //  @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "CPF inválido")
    private String cpf;
    private String nomeSocial;
    private String resideExterior;
    @NotNull @Past private LocalDate dataNascimento;
    private LocalDate dataObito;
    @NotNull private Sexo sexo;
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

    @NotNull private Long paisId;
    private Long profissaoId;
    private GrauDeInstrucao grauDeInstrucao;
    private Long identidadeDeGeneroId;
    private String especificacaoIg;
    private Long orientacaoSexualId;
    private String especificacaoOs;
    private Long corRacaId;
}
