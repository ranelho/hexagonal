package com.rlti.hexagonal.pessoa.adapters.output.repository.entity;

import jakarta.persistence.Entity;
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
@Entity(name = "PAIS")
public class PaisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idPais;
    private String nome;
    private String sigla;
    private String nacionalidade;
}
