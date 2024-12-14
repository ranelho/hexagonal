package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "RESPONSAVEL_LEGAL")
public class ResponsavelLegalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idResponsavelLegal;
    private String nome;
    private String cpf;
    private LocalDate inicioResponsabilidade;
    private LocalDate fimResponsabilidade;

    @OneToOne
    @JoinColumn(name = "id_tipo_responsavel_legal")
    private TipoResponsavelLegalEntity tipoResponsavelLegal;
}
