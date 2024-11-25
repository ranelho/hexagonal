package com.rlti.hexagonal.adapters.output.repository.entity;

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
@Entity(name = "PROFISSAO")
public class ProfissaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idProfissao;
    private String nomeProfissao;
}
