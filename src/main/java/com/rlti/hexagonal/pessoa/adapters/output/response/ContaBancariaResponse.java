package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.ContaBancaria;
import lombok.Data;

import java.util.List;

@Data
public class ContaBancariaResponse {
    private Long idContaBancaria;
    private String numeroConta;
    private String digitoVerificador;
    private String tipoChavePix;
    private String chavePix;

    public ContaBancariaResponse(ContaBancaria contaBancaria) {
        this.idContaBancaria = contaBancaria.getIdContaBancaria();
        this.numeroConta = contaBancaria.getNumeroConta();
        this.digitoVerificador = contaBancaria.getDigitoVerificadorConta();
        this.tipoChavePix = contaBancaria.getTipoChavePix().name();
        this.chavePix = contaBancaria.getNumeroChavePix();
    }

    public static List<ContaBancariaResponse> converte(List<ContaBancaria> contasBancarias) {
        return contasBancarias.stream().map(ContaBancariaResponse::new).toList();
    }
}
