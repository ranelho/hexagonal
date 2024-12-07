package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Telefone;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoTelefone;
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
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa", nullable = true)
    private PessoaEntity pessoa;

    public TelefoneEntity(Telefone telefone) {
        this.tipoTelefone = telefone.getTipoTelefone();
        this.ddi = telefone.getDdi();
        this.ddd = telefone.getDdd();
        this.numeroTelefone = telefone.getNumeroTelefone();
    }
}
