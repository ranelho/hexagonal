package com.rlti.hexagonal.pessoa.adapters.input.controller.request;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;


@Value
public class PessoaRequest {
    @NotNull
    Tipo tipo;
    @NotBlank
    String nome;
    @NotBlank
    String resideExterior;

    @Valid
    PessoaFisicaRequest pessoaFisica;
    @Valid
    PessoaJuridicaRequest pessoaJuridica;
}
