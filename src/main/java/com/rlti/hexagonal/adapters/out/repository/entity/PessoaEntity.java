package com.rlti.hexagonal.adapters.out.repository.entity;

import com.rlti.hexagonal.application.core.domain.Pessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "PESSOA")
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private String nome;

    private String resideExterior;

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private PessoaFisicaEntity pessoaFisica;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private PessoaJuridicaEntity pessoaJuridica;

    public PessoaEntity(Pessoa pessoa) {
        this.idPessoa = pessoa.getIdPessoa();
        this.tipo = pessoa.getTipo();
        this.nome = pessoa.getNome().toUpperCase();
        this.resideExterior = pessoa.getResideExterior().toUpperCase();
        this.createdAt = pessoa.getCreatedAt();
        if (pessoa.getPessoaFisica() != null) {
            this.pessoaFisica = new PessoaFisicaEntity(pessoa.getPessoaFisica());
            this.pessoaFisica.setPessoa(this);
        } else if (pessoa.getPessoaJuridica() != null) {
            this.pessoaJuridica = new PessoaJuridicaEntity(pessoa.getPessoaJuridica());
            this.pessoaJuridica.setPessoa(this);
        }
    }
}


