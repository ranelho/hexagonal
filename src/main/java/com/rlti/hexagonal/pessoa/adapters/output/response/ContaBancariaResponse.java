package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.ContaBancaria;

import java.util.List;

public record ContaBancariaResponse(
        Long idContaBancaria,
        String numeroConta,
        String digitoVerificador,
        String tipoChavePix,
        String chavePix
) {
    public ContaBancariaResponse(ContaBancaria contaBancaria) {
        this(
                contaBancaria.getIdContaBancaria(),
                contaBancaria.getNumeroConta(),
                contaBancaria.getDigitoVerificadorConta(),
                contaBancaria.getTipoChavePix().name(),
                contaBancaria.getNumeroChavePix()
        );
    }

    public static List<ContaBancariaResponse> converte(List<ContaBancaria> contasBancarias) {
        return contasBancarias.stream().map(ContaBancariaResponse::new).toList();
    }
}

