package com.rlti.hexagonal.adapters.input.controller.request;

import lombok.Value;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Value
public class PessoaFisicaRequest {

    @CPF(message = "CPF inválido")
    String cpf;
    LocalDate dataNascimento;
}
