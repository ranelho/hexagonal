package com.rlti.hexagonal.adapters.input.controller.request;

import com.rlti.hexagonal.application.core.domain.enums.TipoTelefone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TelefoneRequest {
    @NotNull
    private TipoTelefone tipoTelefone;
    private String ddi;
    @NotBlank
    private String ddd;
    @NotNull
    private String numeroTelefone;
}
