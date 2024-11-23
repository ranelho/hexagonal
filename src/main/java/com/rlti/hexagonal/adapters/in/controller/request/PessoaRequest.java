package com.rlti.hexagonal.adapters.in.controller.request;

import com.rlti.hexagonal.adapters.out.repository.entity.Tipo;
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

    PessoaFisicaRequest pessoaFisica;
    PessoaJuridicaRequest pessoaJuridica;
}
