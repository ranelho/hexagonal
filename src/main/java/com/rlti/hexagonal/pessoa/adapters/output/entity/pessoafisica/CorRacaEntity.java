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
@Table(name = "TB_COR_RACA")
public class CorRacaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COR_RACA")
    private Long id;

    @Column(name = "DSC_COR_RACA")
    private String corRaca;
}
