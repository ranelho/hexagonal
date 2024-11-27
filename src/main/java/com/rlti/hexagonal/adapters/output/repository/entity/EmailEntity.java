package com.rlti.hexagonal.adapters.output.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EMAIL")
public class EmailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idEmail;
    private String email;
    private String tipoEmail;

    @ManyToOne
    @JoinColumn(name = "id_pessoa_fisica")
    @JsonIgnore
    private PessoaFisicaEntity pessoaFisica;

}
