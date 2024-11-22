package com.rlti.hexagonal.adapters.in.controller.request;

import com.rlti.hexagonal.adapters.out.entity.Tipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class PessoaRequest {
    @NotNull
    private Tipo tipo;
    @NotBlank
    private String nome;
    @NotBlank
    private String resideExterior;
}
