package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.PessoaEntity;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.PessoaJuridica;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "TB_PESSOA_JURIDICA")
public class PessoaJuridicaEntity {

    @Id
    @Column(name = "ID_PESSOA_JURIDICA")
    private Long idPessoa;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ID_PESSOA")
    private PessoaEntity pessoa;

    @CNPJ
    @Column(name= "NUM_CNPJ",unique = true, updatable = false, nullable = false)
    private String cnpj;

    @Column(name = "DSC_RAZAO_SOCIAL", nullable = false)
    private String razaoSocial;

    @Column(name = "NOM_FANTASIA")
    private String nomeFantasia;

    @Column(name = "NUM_INSCRICAO_ESTADUAL")
    private String inscricaoEstadual;

    @Column(name = "NUM_INSCRICAO_MUNICIPAL")
    private String inscricaoMunicipal;

    @Column(name = "STS_SITUACAO")
    private String situacao;

    @Column(name = "NUM_CNPJ_MATRIZ")
    private String cnpjMatriz;

    @Column(name = "TIP_NATUREZA_JURIDICA")
    private String tipoNaturezaJuridica;

    @Column(name = "ID_ITEM_NATUREZA_JURIDICA")
    private Long idItemNaturezaJuridica;

    @OneToMany(mappedBy = "pessoaJuridica", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PessoaJuridicaCnaeEntity> cnaes = new ArrayList<>();

    public PessoaJuridicaEntity(PessoaJuridica pessoaJuridica) {
        this.cnpj = pessoaJuridica.getCnpj();
        this.razaoSocial = pessoaJuridica.getRazaoSocial();
    }
}
