package com.rlti.hexagonal.pessoa.adapters.input.request;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoChavePix;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoContaBancaria;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContaBancariaRequest {
    @NotNull(message = "idAgencia não pode ser nulo")
    private Long idAgencia;
    @NotNull
    private TipoContaBancaria tipoContaBancaria;
    @NotNull
    private String numeroConta;
    @NotNull
    private String digitoVerificadorConta;
    private TipoChavePix tipoChavePix;
    private String numeroChavePix;
}
