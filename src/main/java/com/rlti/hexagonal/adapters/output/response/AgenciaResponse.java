package com.rlti.hexagonal.adapters.output.response;

import lombok.Data;

@Data
public class AgenciaResponse {
    private Long idAgencia; private String agencia;
    private String digitoVerificadorAgencia;
    private String nomeAgencia;
}
