package com.rlti.hexagonal.adapters.out.repository.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CNPJ
    @Column(unique = true)
    private String cnpj;
    private String razaoSocial;

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoa;

    public PessoaJuridicaEntity(PessoaJuridica pessoaJuridica) {
        this.cnpj = pessoaJuridica.getCnpj();
        this.razaoSocial = pessoaJuridica.getRazaoSocial().toUpperCase();
    }
}
