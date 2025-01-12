package com.rlti.hexagonal.pessoa.adapters.output.response;

import java.time.LocalDate;

public interface PessoaFisicaProjection {
    Long getIdPessoaFisica();
    String getNome();
    LocalDate getDataNascimento();
    String getSexo();
    String getCpf();
    String getNacionalidade();
    String getEmail();
}
