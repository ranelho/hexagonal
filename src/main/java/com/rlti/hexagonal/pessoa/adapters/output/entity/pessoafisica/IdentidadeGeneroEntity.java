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
@Entity
@Table(name = "TB_IDENTIDADE_GENERO")
public class IdentidadeGeneroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_IDENTIDADE_GENERO")
    private Long idIdentidadeGenero;

    @Column(name = "DSC_IDENTIDADE_GENERO")
    private String nomeIdentidadeGenero;
}
