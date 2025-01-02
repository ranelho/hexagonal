package com.rlti.hexagonal.pessoa.adapters.output.entity.banco;

import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.PessoaEntity;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoChavePix;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoContaBancaria;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.ContaBancaria;
import com.rlti.hexagonal.pessoa.config.PostgreSQLEnumConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CONTA_BANCARIA")
public class ContaBancariaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTA_BANCARIA")
    @SequenceGenerator(name = "SEQ_CONTA_BANCARIA", sequenceName = "TB_CONTA_BANCARIA_ID_CONTA_BANCARIA_seq", allocationSize = 1)
    @Column(name = "ID_CONTA_BANCARIA")
    private Long idContaBancaria;

    @Column(name = "NUM_CONTA_BANCARIA")
    private String numeroConta;

    @Column(name = "NUM_DIGITO_VERIF_CONTA")
    private String digitoVerificadorConta;

    @Enumerated(EnumType.STRING) @Column(name = "TP_CHAVE_PIX") @Convert(converter = PostgreSQLEnumConverter.class)
    private TipoChavePix tipoChavePix;

    @Enumerated(EnumType.STRING) @Column(name = "TP_CONTA_BANCARIA") @Convert(converter = PostgreSQLEnumConverter.class)
    private TipoContaBancaria tipoContaBancaria;

    @Column(name = "NUM_CHAVE_PIX")
    private String numeroChavePix;

    @ManyToOne
    @JoinColumn(name = "ID_AGENCIA", referencedColumnName = "ID_AGENCIA")
    private AgenciaEntity agencia;

    @ManyToOne
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA")
    private PessoaEntity pessoa;

    public ContaBancariaEntity(PessoaEntity pessoa, ContaBancaria contaBancaria, AgenciaEntity agencia) {
        this.numeroConta = contaBancaria.getNumeroConta();
        this.digitoVerificadorConta = contaBancaria.getDigitoVerificadorConta();
        this.tipoChavePix = contaBancaria.getTipoChavePix();
        this.numeroChavePix = contaBancaria.getNumeroChavePix();
        this.agencia = agencia;
        this.pessoa = pessoa;
    }
}
