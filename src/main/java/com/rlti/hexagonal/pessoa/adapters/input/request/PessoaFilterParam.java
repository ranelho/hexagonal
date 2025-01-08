package com.rlti.hexagonal.pessoa.adapters.input.request;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.Sexo;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class PessoaFilterParam {
    private String cpf;
    private String nome;
    private Sexo sexo;
    private LocalDate dataNascimento;

    public void setNome(String nome) {
        this.nome = nome != null ? nome.toUpperCase() : null;
    }

    public String getDataFormatada(){
        return dataNascimento != null ? dataNascimento.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
    }
}
