package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa;


import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoLogradouro;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Endereco;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoEndereco;
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
@Table(name = "TB_ENDERECO")
public class EnderecoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_ENDERECO")
    private Long idEndereco;

    @Column(name = "NUM_CEP")
    private String cep;

    @Column(name = "DSC_LOGRADOURO")
    private String logradouro;

    @Column(name = "NUM_ENDERECO")
    private String numero;

    @Column(name = "DSC_COMPLEMENTO")
    private String complemento;

    @Column(name = "NOM_BAIRRO")
    private String bairro;

    @Column(name = "DSC_MUNICIPIO")
    private String cidade;

    @Column(name = "SG_UF")
    private String uf;

    @Enumerated(EnumType.STRING) @Convert(converter = PostgreSQLEnumConverter.class) @Column(name = "TIP_ENDERECO")
    private TipoEndereco tipoEndereco;

    @Enumerated(EnumType.STRING) @Convert(converter = PostgreSQLEnumConverter.class) @Column(name = "TIP_LOGRADOURO")
    private TipoLogradouro tipoLogradouro;

    @Column(name = "ST_INDICADOR_CORRESPONDENCIA")
    private boolean indicadorCorrespondencia;

    @Column(name = "ST_INDICADOR_FATURAMENTO")
    private boolean indicadorFaturamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA")
    private PessoaEntity pessoa;

    @Column(name = "ID_PAIS")
    private Long idPais;

    public EnderecoEntity(PessoaEntity pessoa, Endereco endereco) {
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getLocalidade();
        this.uf = endereco.getUf();
        this.tipoEndereco = endereco.getTipoEndereco();
        this.indicadorCorrespondencia = endereco.isIndicadorCorrespondencia();
        this.indicadorFaturamento = endereco.isIndicadorFaturamento();
        this.pessoa = pessoa;
    }

    public EnderecoEntity(Endereco endereco) {
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getLocalidade();
        this.uf = endereco.getUf();
        this.tipoEndereco = endereco.getTipoEndereco();
        this.indicadorCorrespondencia = endereco.isIndicadorCorrespondencia();
        this.indicadorFaturamento = endereco.isIndicadorFaturamento();
    }
}
