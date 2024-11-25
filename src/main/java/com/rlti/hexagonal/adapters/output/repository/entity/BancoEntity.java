package com.rlti.hexagonal.adapters.output.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BANCO")
public class BancoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idBanco;
    private String nome;
    private String codigo;
    private String cnpj;
    private String inscricaoEstadual;

    @OneToMany(mappedBy = "banco", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<AgenciaEntity> agencias;
}
