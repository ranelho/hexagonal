package com.rlti.hexagonal.adapters.output.repository.entity;

import com.rlti.hexagonal.adapters.input.controller.request.ContaBancariaRequest;
import com.rlti.hexagonal.application.core.domain.ContaBancaria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CONTA_BANCARIA")
public class ContaBancariaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idContaBancaria;
    private String numeroConta;
    private String digitoVerificador;
    private String tipoChavePix;
    private String chavePix;

    @ManyToOne(fetch = FetchType.LAZY)
    private AgenciaEntity agencia;

    @ManyToOne (fetch = FetchType.LAZY)
    private PessoaEntity pessoa;

    public ContaBancariaEntity(PessoaEntity pessoa, ContaBancaria contaBancaria, AgenciaEntity agencia) {
        this.numeroConta = contaBancaria.getNumeroConta();
        this.digitoVerificador = contaBancaria.getDigitoVerificador();
        this.tipoChavePix = contaBancaria.getTipoChavePix();
        this.chavePix = contaBancaria.getChavePix();
        this.agencia = agencia;
        this.pessoa = pessoa;
    }
}
