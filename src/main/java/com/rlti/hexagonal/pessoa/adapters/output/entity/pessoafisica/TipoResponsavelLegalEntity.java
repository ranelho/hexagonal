package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB_IPO_RESPONSAVEL_LEGAL")
public class TipoResponsavelLegalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idTipoResponsavelLegal;
    private String descricao;
}
