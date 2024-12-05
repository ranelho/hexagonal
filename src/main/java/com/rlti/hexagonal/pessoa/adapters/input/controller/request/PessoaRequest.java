package com.rlti.hexagonal.pessoa.adapters.input.controller.request;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.List;


@Value
public class PessoaRequest {
    @NotNull
    Tipo tipo;
    @NotBlank
    String nome;

    List<TelefoneRequest> telefones;
    List<EmailRequest> emails;
    List<EnderecoRequest> enderecos;

    @Valid
    PessoaFisicaRequest pessoaFisica;
    @Valid
    PessoaJuridicaRequest pessoaJuridica;
}
