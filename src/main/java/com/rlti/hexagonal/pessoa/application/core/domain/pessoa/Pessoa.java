package com.rlti.hexagonal.pessoa.application.core.domain.pessoa;

import com.rlti.hexagonal.pessoa.adapters.input.request.PessoaFisicaRequest;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.PessoaFisica;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.PessoaJuridica;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data //todo - remove lombok
public class Pessoa {
    private Long idPessoa;
    private Tipo tipo;
    private String nome;

    private LocalDateTime createdAt;
    private PessoaFisica pessoaFisica;
    private PessoaJuridica pessoaJuridica;

    private List<Endereco> enderecos;
    private List<Telefone> telefones;
    private List<Email> emails;
    private List<ContaBancaria> contas;

    public Pessoa() {
    }

    public Pessoa(PessoaFisicaRequest request) {
        getComumPessoa(request);
        this.pessoaFisica = new PessoaFisica(request);
    }

    private void getComumPessoa(PessoaFisicaRequest request) {
        this.tipo = request.getTipo();
        this.nome = request.getNome().toUpperCase();

        this.enderecos = request.getEnderecos().stream()
                .map(Endereco::new)
                .toList();

        this.telefones = request.getTelefones().stream()
                .map(Telefone::new)
                .toList();

        this.emails = request.getEmails().stream()
                .map(Email::new)
                .toList();
    }

}
