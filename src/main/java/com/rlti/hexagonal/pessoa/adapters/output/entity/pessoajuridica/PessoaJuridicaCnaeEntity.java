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
@Entity(name = "TB_PESSOA_JURIDICA_CNAE")
public class PessoaJuridicaCnaeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PESSOA_JURIDICA_CNAE")
    private Long idPessoaJuridicaCnae;

    @ManyToOne
    @JoinColumn(name = "ID_PESSOA_JURIDICA", nullable = false)
    private PessoaJuridicaEntity pessoaJuridica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CNAE", nullable = false)
    @JsonIgnore
    private CnaeEntity cnae;

    @Column(nullable = false)
    private boolean isPrincipal;
}
