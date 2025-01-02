package com.rlti.hexagonal.pessoa.application.core.domain.pessoa;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoChavePix;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoContaBancaria;
import lombok.Data;

@Data
public class ContaBancaria {
    private Long idContaBancaria;
    private Pessoa pessoa;
    private Agencia agencia;
    private TipoContaBancaria tipoContaBancaria;
    private String numeroConta;
    private String digitoVerificadorConta;
    private TipoChavePix tipoChavePix;
    private String numeroChavePix;

    public ContaBancaria() {
    }

}
