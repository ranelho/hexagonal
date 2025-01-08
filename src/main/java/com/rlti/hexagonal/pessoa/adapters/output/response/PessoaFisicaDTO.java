package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.Sexo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PessoaFisicaDTO {
    private Long idPessoaFisica;
    private String nome;
    private LocalDate dataNascimento;
    private Sexo sexo;
    private String cpf;
    private String nacionalidade;
    private String email;

    public PessoaFisicaDTO(Long idPessoaFisica, String nome, LocalDate dataNascimento, Sexo sexo, String cpf, String nacionalidade, String email) {
        this.idPessoaFisica = idPessoaFisica;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.cpf = cpf;
        this.nacionalidade = nacionalidade;
        this.email = email;
    }
}
