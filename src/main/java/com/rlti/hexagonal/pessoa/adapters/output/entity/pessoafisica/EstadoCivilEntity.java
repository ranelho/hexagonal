package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.EstadoCivil;
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
@Table(name = "TB_ESTADO_CIVIL")
public class EstadoCivilEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_ESTADO_CIVIL")
    Long idEstadoCivil;

    @Column(name = "DSC_ESTADO_CIVIL")
    String nomeEstadoCivil;

    public EstadoCivilEntity(Long idPessoaFisica) {
        this.idEstadoCivil = idPessoaFisica;
    }

    public EstadoCivilEntity(EstadoCivil estadoCivil) {
        this.nomeEstadoCivil = estadoCivil.getNomeEstadoCivil();
    }
}
