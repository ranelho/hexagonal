package com.rlti.hexagonal.pessoa.adapters.output.entity.banco;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Agencia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_AGENCIA")
public class AgenciaEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    @Column(name = "ID_AGENCIA")
    private Long idAgencia;

    @Column(name = "NUM_AGENCIA")
    private String agencia;

    @Column(name = "NUM_DIGITO_VERIFICADOR_AGENCIA")
    private String digitoVerificadorAgencia;

    @Column(name = "NOM_AGENCIA")
    private String nomeAgencia;

    @Column(name = "DSC_LOGRADOURO")
    private String logradouro;

    @Column(name = "NOM_BAIRRO")
    private String bairro;

    @Column(name = "DSC_MUNICIPIO")
    private String cidade;

    @Column(name = "SG_UF")
    private String uf;

    @Column(name = "NUM_CEP")
    private String cep;

    @Column(name = "NUM_ENDERECO")
    private String numero;

    @Column(name = "STS_INDICADOR_INATIVACAO")
    private Boolean indicadorInativacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BANCO", referencedColumnName = "ID_BANCO")
    private BancoEntity banco;

    @OneToMany(mappedBy = "agencia", fetch = FetchType.LAZY)
    List<ContaBancariaEntity> contasBancarias;

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
        this.numero = agencia.getNumero().toUpperCase();
    }
}
