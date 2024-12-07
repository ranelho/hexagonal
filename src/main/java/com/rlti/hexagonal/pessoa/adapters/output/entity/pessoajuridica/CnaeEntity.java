package com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.Cnae;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cnaes")
public class CnaeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String subclasse;
    private String denominacao;

    @OneToMany(mappedBy = "cnae", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaJuridicaCnaeEntity> pessoasJuridicas = new ArrayList<>();

    public CnaeEntity(Cnae cnae) {
        this.subclasse = cnae.getSubclasse();
        this.denominacao = cnae.getDenominacao();
    }
}
