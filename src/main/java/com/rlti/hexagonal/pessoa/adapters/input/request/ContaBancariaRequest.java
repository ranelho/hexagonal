package com.rlti.hexagonal.pessoa.adapters.input.request;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoChavePix;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoContaBancaria;
import lombok.Data;

@Data
public class ContaBancariaRequest {
    private Long idAgencia;
    private TipoContaBancaria tipoContaBancaria;
    private String numeroConta;
    private String digitoVerificadorConta;
    private TipoChavePix tipoChavePix;
    private String numeroChavePix;
}
