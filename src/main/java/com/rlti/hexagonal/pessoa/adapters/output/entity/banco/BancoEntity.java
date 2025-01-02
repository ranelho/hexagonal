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
    @Column(name = "ID_BANCO")
    private Long idBanco;

    @Column(name = "NOM_BANCO")
    private String nome;

    @Column(name = "NUM_BANCO")
    private String codigo;

    @Column(name = "NUM_CNPJ")
    private String cnpj;

    @Column(name = "STS_INDICADOR_INATIVACAO")
    private boolean indicadorInativacao;

    @OneToMany(mappedBy = "banco", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<AgenciaEntity> agencias;

    public void update(Banco banco) {
        this.nome = banco.getNome();
        this.codigo = banco.getCodigo();
        this.cnpj = banco.getCnpj();
        this.indicadorInativacao = banco.isIndicadorInativacao();
    }

}
