package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica;

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
@Entity(name = "ORIENTACAO_SEXUAL")
public class OrientacaoSexualEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrientacaoSexual;
    private String descricao;
}
