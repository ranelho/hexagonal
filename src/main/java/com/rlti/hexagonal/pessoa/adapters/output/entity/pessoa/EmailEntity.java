package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Email;
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
@Entity(name = "TB_EMAIL")
public class EmailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idEmail;
    private String email;
    @Enumerated(EnumType.STRING)
    private TipoEmail tipoEmail;

    @ManyToOne
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA")
    @JsonIgnore
    private PessoaEntity pessoa;

    public EmailEntity(Email email) {
        this.email = email.getEmail();
        this.tipoEmail = email.getTipoEmail();
    }

    public EmailEntity(Email email, PessoaEntity pessoa) {
        this.email = email.getEmail();
        this.tipoEmail = email.getTipoEmail();
        this.pessoa = pessoa;
    }
}
