package com.rlti.hexagonal.adapters.output.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "PESSOA_JURIDICA")
public class PessoaJuridicaEntity {

    @Id
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
}
