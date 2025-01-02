package com.rlti.hexagonal.pessoa.adapters.output.entity.banco;

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
@Table(name = "TB_PAIS")
public class PaisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_PAIS")
    private Long idPais;

    @Column(name = "NOM_PAIS")
    private String nomePais;

    @Column(name = "DSC_ISO_ALPHA2")
    private String isoAlpha2;

    @Column(name = "DSC_ISO_ALPHA3")
    private String isoAlpha3;

    @Column(name = "DSC_NACIONALIDADE")
    private String nacionalidade;
}
