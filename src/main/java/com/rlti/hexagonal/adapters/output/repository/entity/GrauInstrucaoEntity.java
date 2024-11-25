package com.rlti.hexagonal.adapters.output.repository.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GrauInstrucaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idGrauInstrucao;
    private String grauInstrucao;
}
