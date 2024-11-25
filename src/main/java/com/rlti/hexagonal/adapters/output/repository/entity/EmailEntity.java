package com.rlti.hexagonal.adapters.output.repository.entity;

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
    private PessoaEntity pessoa;

}
