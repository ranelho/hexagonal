package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.PessoaFisica;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

@Data
public class PessoaFisicaResponse {
    String cpf;
    String nome;
    LocalDate dataNascimento;
    String sexo;

    public PessoaFisicaResponse(PessoaFisica pessoaFisica) {
        this.cpf = pessoaFisica.getCpf();
        this.nome = pessoaFisica.getPessoa().getNome();
        this.dataNascimento = pessoaFisica.getDataNascimento();
        this.sexo = pessoaFisica.getSexo().toString();
    }

    public static Page<PessoaFisicaResponse> converte(Page<PessoaFisica> pessoas) {
        return pessoas.map(PessoaFisicaResponse::new);
    }
}
