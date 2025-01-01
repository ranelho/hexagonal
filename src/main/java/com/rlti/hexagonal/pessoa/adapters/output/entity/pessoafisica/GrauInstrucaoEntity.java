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
@Table(name = "TB_GRAU_INSTRUCAO")
public class GrauInstrucaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_GRAU_INSTRUCAO")
    private Long idGrauInstrucao;

    @Column(name = "DSC_GRAU_INSTRUCAO")
    private String grauInstrucao;
}
