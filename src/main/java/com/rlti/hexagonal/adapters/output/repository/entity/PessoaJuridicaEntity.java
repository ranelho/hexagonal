package com.rlti.hexagonal.adapters.output.repository.entity;

import com.rlti.hexagonal.application.core.domain.PessoaJuridica;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PESSOA_JURIDICA")
public class PessoaJuridicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idPessoaJuridica;

    @CNPJ
    @Column(unique = true, updatable = false, nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String razaoSocial;

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoa;

    public PessoaJuridicaEntity(PessoaJuridica pessoaJuridica) {
        this.cnpj = pessoaJuridica.getCnpj();
        this.razaoSocial = pessoaJuridica.getRazaoSocial().toUpperCase();
    }
}
