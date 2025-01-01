package com.rlti.hexagonal.pessoa.adapters.output.entity.banco;

import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.PessoaEntity;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.ContaBancaria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_CONTA_BANCARIA")
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
