package com.rlti.hexagonal.pessoa.adapters.input.request;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.Sexo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaFilterParam {
    private String cpf;
    private String nome;
    private Sexo sexo;
    private LocalDate dataNascimento;
}
