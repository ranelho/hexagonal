package com.rlti.hexagonal.adapters.output.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cnaes")
public class CnaeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String subclasse;

    private String denominacao;

    private Boolean principal;

    @OneToMany(mappedBy = "cnae", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaJuridicaCnaeEntity> pessoasJuridicas = new ArrayList<>();
}
