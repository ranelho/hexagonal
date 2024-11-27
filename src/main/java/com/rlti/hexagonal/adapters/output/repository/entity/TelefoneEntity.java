package com.rlti.hexagonal.adapters.output.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rlti.hexagonal.application.core.domain.PessoaFisica;
import com.rlti.hexagonal.application.core.domain.Telefone;
import com.rlti.hexagonal.application.core.domain.enums.TipoTelefone;
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
    @JoinColumn(name = "id_pessoa_fisica")
    @JsonIgnore
    private PessoaFisicaEntity pessoaFisica;

    public TelefoneEntity(Telefone telefone) {
        this.tipoTelefone = telefone.getTipoTelefone();
        this.ddi = telefone.getDdi();
        this.ddd = telefone.getDdd();
        this.numeroTelefone = telefone.getNumeroTelefone();
    }
}
