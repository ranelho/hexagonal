package com.rlti.hexagonal.adapters.output.repository.entity;

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

}
