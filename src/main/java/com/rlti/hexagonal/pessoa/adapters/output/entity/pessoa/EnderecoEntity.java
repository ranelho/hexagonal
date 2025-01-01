package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa;


import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Endereco;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoEndereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_ENDERECO")
public class EnderecoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idEndereco;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private TipoEndereco tipoEndereco;
    private String indicadorCorrespondencia;
    private String indicadorFaturamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA")
    private PessoaEntity pessoa;

    public EnderecoEntity(PessoaEntity pessoa, Endereco endereco) {
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getLocalidade();
        this.uf = endereco.getUf();
        this.tipoEndereco = endereco.getTipoEndereco();
        this.indicadorCorrespondencia = endereco.getIndicadorCorrespondencia();
        this.indicadorFaturamento = endereco.getIndicadorFaturamento();
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
        this.indicadorCorrespondencia = endereco.getIndicadorCorrespondencia();
        this.indicadorFaturamento = endereco.getIndicadorFaturamento();
    }
}
