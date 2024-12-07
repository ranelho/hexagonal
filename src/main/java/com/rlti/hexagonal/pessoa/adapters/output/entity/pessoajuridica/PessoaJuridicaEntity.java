package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica;

import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.PessoaEntity;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.PessoaJuridica;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "PESSOA_JURIDICA")
public class PessoaJuridicaEntity {

    @Id
    @Column(name = "id_pessoa")
    private Long idPessoa;

    @OneToOne
    @MapsId
    @JoinColumn(name = "idPessoa")
    private PessoaEntity pessoa;

    @CNPJ
    @Column(unique = true, updatable = false, nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String razaoSocial;
    private LocalDate dataAbertura;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String situacao;

    @OneToMany(mappedBy = "pessoaJuridica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaJuridicaCnaeEntity> cnaes = new ArrayList<>();

    public PessoaJuridicaEntity(PessoaJuridica pessoaJuridica) {
        this.cnpj = pessoaJuridica.getCnpj();
        this.razaoSocial = pessoaJuridica.getRazaoSocial();
    }
}
