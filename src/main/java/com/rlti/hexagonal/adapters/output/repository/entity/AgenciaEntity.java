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
@Entity(name = "AGENCIA_BANCARIA")
public class AgenciaEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private Long idAgencia;
    private String agencia;
    private String digitoVerificadorAgencia;
    private String nomeAgencia;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String pais;
    private String numero;

    @ManyToOne(fetch = FetchType.LAZY)
    private BancoEntity banco;
}
