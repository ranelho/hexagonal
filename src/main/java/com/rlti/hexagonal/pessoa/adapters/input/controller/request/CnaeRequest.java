package com.rlti.hexagonal.pessoa.adapters.input.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CnaeRequest {
    @NotNull
    private String subclasse;
    private boolean isPrincipal;
}
