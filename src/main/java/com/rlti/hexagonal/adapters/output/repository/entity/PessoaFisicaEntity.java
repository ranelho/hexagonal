package com.rlti.hexagonal.adapters.output.repository.entity;

import com.rlti.hexagonal.application.core.domain.PessoaFisica;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PESSOA_FISICA")
public class PessoaFisicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CPF
    @Column(unique = true)
    private String cpf;
    private LocalDate dataNascimento;

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoa;

    public PessoaFisicaEntity(PessoaFisica pessoaFisica) {
        this.cpf = pessoaFisica.getCpf();
        this.dataNascimento = pessoaFisica.getDataNascimento();
    }
}
