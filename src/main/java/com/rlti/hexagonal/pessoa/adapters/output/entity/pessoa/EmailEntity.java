package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Email;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoEmail;
import com.rlti.hexagonal.pessoa.config.GenericEnumConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_E_MAIL")
public class EmailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_EMAIL")
    private Long idEmail;

    @Column(name = "DSC_E_MAIL")
    private String email;

   // @Enumerated(EnumType.STRING)  @Convert(converter = GenericEnumConverter.class)
    @Column(name = "TIP_EMAIL")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Basic(optional = false)
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
