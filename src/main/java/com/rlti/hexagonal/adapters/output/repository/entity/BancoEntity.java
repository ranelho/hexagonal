package com.rlti.hexagonal.adapters.output.repository.entity;

import com.rlti.hexagonal.application.core.domain.Agencia;
import com.rlti.hexagonal.application.core.domain.Banco;
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

    public void update(Banco banco) {
        this.nome = banco.getNome();
        this.codigo = banco.getCodigo();
        this.cnpj = banco.getCnpj();
        this.inscricaoEstadual = banco.getInscricaoEstadual();
    }

}
