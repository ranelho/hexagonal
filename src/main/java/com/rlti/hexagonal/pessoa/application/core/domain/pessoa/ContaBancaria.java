package com.rlti.hexagonal.pessoa.application.core.domain.pessoa;

import com.rlti.hexagonal.pessoa.adapters.input.request.ContaBancariaRequest;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoChavePix;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoContaBancaria;
import jakarta.validation.Valid;
import lombok.Data;

@Data //todo - remove lombok
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

    public ContaBancaria(@Valid ContaBancariaRequest contaBancaria) {
        this.tipoContaBancaria = contaBancaria.getTipoContaBancaria();
        this.numeroConta = contaBancaria.getNumeroConta();
        this.digitoVerificadorConta = contaBancaria.getDigitoVerificadorConta();
        this.tipoChavePix = contaBancaria.getTipoChavePix();
        this.numeroChavePix = contaBancaria.getNumeroChavePix();
        this.agencia = new Agencia(contaBancaria.getIdAgencia());
    }
}
