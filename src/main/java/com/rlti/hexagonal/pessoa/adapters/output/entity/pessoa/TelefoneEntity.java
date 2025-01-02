package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Telefone;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoTelefone;
import com.rlti.hexagonal.pessoa.config.PostgreSQLEnumConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Enumerated(EnumType.STRING) @Column(name = "TP_TELEFONE_CONTATO") @Convert(converter = PostgreSQLEnumConverter.class)
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

    public TelefoneEntity(Telefone telefone) {
        this.tipoTelefone = telefone.getTipoTelefone();
        this.ddi = telefone.getDdi();
        this.ddd = telefone.getDdd();
        this.numeroTelefone = telefone.getNumeroTelefone();
    }
}
