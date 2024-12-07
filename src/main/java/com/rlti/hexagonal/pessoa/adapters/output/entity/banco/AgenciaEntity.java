package com.rlti.hexagonal.pessoa.adapters.output.entity.banco;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Agencia;
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

    public AgenciaEntity(BancoEntity bancoEntity, Agencia agencia) {
        this.banco = bancoEntity;
        this.agencia = agencia.getAgencia();
        this.digitoVerificadorAgencia = agencia.getDigitoVerificadorAgencia();
        this.nomeAgencia = agencia.getNomeAgencia().toUpperCase();
        this.logradouro = agencia.getLogradouro().toUpperCase();
        this.bairro = agencia.getBairro().toUpperCase();
        this.cidade = agencia.getCidade().toUpperCase();
        this.uf = agencia.getUf().toUpperCase();
        this.cep = agencia.getCep();
        this.pais = agencia.getPais().toUpperCase();
        this.numero = agencia.getNumero().toUpperCase();
    }

    public void update(Agencia agencia) {
        this.agencia = agencia.getAgencia();
        this.digitoVerificadorAgencia = agencia.getDigitoVerificadorAgencia();
        this.nomeAgencia = agencia.getNomeAgencia().toUpperCase();
        this.logradouro = agencia.getLogradouro().toUpperCase();
        this.bairro = agencia.getBairro().toUpperCase();
        this.cidade = agencia.getCidade().toUpperCase();
        this.uf = agencia.getUf().toUpperCase();
        this.cep = agencia.getCep();
        this.pais = agencia.getPais().toUpperCase();
        this.numero = agencia.getNumero().toUpperCase();
    }
}
