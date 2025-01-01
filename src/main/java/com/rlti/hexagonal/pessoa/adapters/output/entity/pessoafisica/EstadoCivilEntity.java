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
@Entity(name = "TB_ESTADO_CIVIL")
public class EstadoCivilEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_ESTADO_CIVIL")
    Long idEstadoCivil;

    @Column(name = "DSC_ESTADO_CIVIL")
    String nomeEstadoCivil;
}
