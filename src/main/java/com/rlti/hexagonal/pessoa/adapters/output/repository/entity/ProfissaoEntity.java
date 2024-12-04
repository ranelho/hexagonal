package com.rlti.hexagonal.pessoa.adapters.output.repository.entity;

import com.rlti.hexagonal.pessoa.application.core.domain.Profissao;
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
@NoArgsConstructor
@Entity(name = "PROFISSAO")
public class ProfissaoEntity {
    @Id
    private Long codigo;
    private String nomeProfissao;

    public ProfissaoEntity(Long codigo, String titulo) {
        this.codigo =  codigo;
        this.nomeProfissao = titulo;
    }

    public ProfissaoEntity(Profissao profissao) {
        this.codigo = profissao.getCodigo();
        this.nomeProfissao = profissao.getNomeProfissao();
    }
}
