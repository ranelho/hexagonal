package com.rlti.hexagonal.pessoa.adapters.output.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rlti.hexagonal.pessoa.application.core.domain.Email;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoEmail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EMAIL")
public class EmailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idEmail;
    private String email;
    @Enumerated(EnumType.STRING)
    private TipoEmail tipoEmail;

    @ManyToOne
    @JoinColumn(name = "id_pessoa_fisica")
    @JsonIgnore
    private PessoaFisicaEntity pessoaFisica;

    public EmailEntity(Email email) {
        this.email = email.getEmail();
        this.tipoEmail = email.getTipoEmail();
    }
}
