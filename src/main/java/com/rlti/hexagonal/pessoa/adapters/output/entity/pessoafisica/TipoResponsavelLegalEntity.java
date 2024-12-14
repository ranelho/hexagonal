package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "TIPO_RESPONSAVEL_LEGAL")
public class TipoResponsavelLegalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idTipoResponsavelLegal;
    private String descricao;
}
