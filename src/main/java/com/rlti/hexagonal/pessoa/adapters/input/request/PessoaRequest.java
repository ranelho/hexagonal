package com.rlti.hexagonal.pessoa.adapters.input.request;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class PessoaRequest {
    @NotNull
    private Tipo tipo;

    @NotBlank
    private String nome;

    private List<@Valid TelefoneRequest> telefones;
    private List<@Valid EmailRequest> emails;
    private List<@Valid EnderecoRequest> enderecos;
    private @Valid ContaBancariaRequest contaBancaria;
}