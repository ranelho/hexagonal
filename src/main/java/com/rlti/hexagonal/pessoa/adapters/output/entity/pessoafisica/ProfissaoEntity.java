package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.Profissao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB_PROFISSAO")
public class ProfissaoEntity {
    @Id
    @Column(name = "ID_PROFISSAO")
    private Long idProfissao;

    @Column(name = "NUM_PROFISSAO")
    private String numeroProfissao;

    @Column(name = "DSC_PROFISSAO")
    private String nomeProfissao;

    public ProfissaoEntity(Profissao profissao) {
        this.numeroProfissao = profissao.getNomeProfissao();
        this.nomeProfissao = profissao.getNomeProfissao();
    }
}
