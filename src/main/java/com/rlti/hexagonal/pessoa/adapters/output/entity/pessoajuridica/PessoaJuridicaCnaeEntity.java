package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pessoa_juridica_cnae")
public class PessoaJuridicaCnaeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_juridica_id", nullable = false)
    private PessoaJuridicaEntity pessoaJuridica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cnae_id", nullable = false)
    @JsonIgnore
    private CnaeEntity cnae;

    @Column(nullable = false)
    private boolean isPrincipal;
}
