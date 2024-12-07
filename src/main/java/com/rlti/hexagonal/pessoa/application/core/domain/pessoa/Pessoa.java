package com.rlti.hexagonal.pessoa.application.core.domain.pessoa;

import com.rlti.hexagonal.pessoa.adapters.input.request.PessoaRequest;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.PessoaFisica;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.PessoaJuridica;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public Pessoa() {
    }

    public Pessoa(PessoaRequest request) {
        this.tipo = request.getTipo();
        this.nome = request.getNome().toUpperCase();

        this.enderecos = request.getEnderecos().stream()
                .map(Endereco::new)
                .collect(Collectors.toList());

        this.telefones = request.getTelefones().stream()
                .map(Telefone::new)
                .collect(Collectors.toList());

        this.emails = request.getEmails().stream()
                .map(Email::new)
                .collect(Collectors.toList());
        if (Tipo.FISICA == request.getTipo()){
            this.pessoaFisica = new PessoaFisica(request.getPessoaFisica());
        } else {
            this.pessoaJuridica = new PessoaJuridica(request.getPessoaJuridica());
        }
    }

    public Pessoa(Long idPessoa, Tipo tipo, String nome, LocalDateTime createdAt, PessoaFisica pessoaFisica, PessoaJuridica pessoaJuridica, List<Endereco> enderecos, List<Telefone> telefones, List<Email> emails) {
        this.idPessoa = idPessoa;
        this.tipo = tipo;
        this.nome = nome;
        this.createdAt = createdAt;
        this.pessoaFisica = pessoaFisica;
        this.pessoaJuridica = pessoaJuridica;
        this.enderecos = enderecos;
        this.telefones = telefones;
        this.emails = emails;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }
}
