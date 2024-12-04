package com.rlti.hexagonal.pessoa.adapters.input.controller.request;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoChavePix;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoConta;
import lombok.Data;

@Data
public class ContaBancariaRequest {
    private Long idAgencia;
    private TipoConta tipoConta;
    private String numeroConta;
    private String digitoVerificador;
    private TipoChavePix tipoChavePix;
    private String chavePix;
}
