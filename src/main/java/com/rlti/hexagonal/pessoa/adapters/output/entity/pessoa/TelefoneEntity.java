package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Telefone;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoTelefone;
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
@Entity(name = "TB_TELEFONE_CONTATO")
public class TelefoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_TELEFONE")
    private Long idTelefone;

    //@Enumerated(EnumType.STRING)  @Convert(converter = GenericEnumConverter.class)
    @Column(name = "TP_TELEFONE_CONTATO")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Basic(optional = false)
    private TipoTelefone tipoTelefone;

    @Column(name = "NUM_DDI")
    private String ddi;

    @Column(name = "NUM_DDD")
    private String ddd;

    @Column(name = "NUM_TELEFONE_CONTATO")
    private String numeroTelefone;

    @ManyToOne
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA")
    private PessoaEntity pessoa;

    public TelefoneEntity(Telefone telefone, PessoaEntity pessoaEntity) {
        this.tipoTelefone = telefone.getTipoTelefone();
        this.ddi = telefone.getDdi();
        this.ddd = telefone.getDdd();
        this.numeroTelefone = telefone.getNumeroTelefone();
        this.pessoa = pessoaEntity;
    }
}
