package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.Sexo;

import java.time.LocalDate;

public record PessoaFisicaDTO(
        Long idPessoaFisica,
        String nome,
        LocalDate dataNascimento,
        Sexo sexo,
        String cpf,
        String nacionalidade,
        String email
) {}
