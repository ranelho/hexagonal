package com.rlti.hexagonal.pessoa.adapters.output.entity.banco;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Banco;
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
@Entity
@Table(name = "TB_BANCO")
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
