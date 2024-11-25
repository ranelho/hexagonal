package com.rlti.hexagonal.adapters.output.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rlti.hexagonal.application.core.domain.TipoTelefone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TELEFONE")
public class TelefoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idTelefone;
    @Enumerated(EnumType.STRING)
    private TipoTelefone tipoTelefone;
    private String ddi;
    private String ddd;
    private String numeroTelefone;

    @ManyToOne
    @JoinColumn(name = "pessoa_fisica_id")
    @JsonIgnore
    private PessoaEntity pessoa;
}
