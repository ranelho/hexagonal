package com.rlti.hexagonal.adapters.input.controller.request;

import com.rlti.hexagonal.application.core.domain.Sexo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PessoaFisicaRequest {

    @NotNull @CPF(message = "CPF inválido")
    private String cpf;
    private String nomeAbreviado;
    private String nomeSocial;
    @NotNull
    private LocalDate dataNascimento;
    private LocalDate dataObito;
    @NotNull
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
}
