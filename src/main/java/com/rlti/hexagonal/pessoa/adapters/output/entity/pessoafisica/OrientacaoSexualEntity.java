package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_ORIENTACAO_SEXUAL")
public class OrientacaoSexualEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORIENTACAO_SEXUAL")
    private Long idOrientacaoSexual;

    @Column(name = "DSC_ORIENTACAO_SEXUAL")
    private String descricao;
}
