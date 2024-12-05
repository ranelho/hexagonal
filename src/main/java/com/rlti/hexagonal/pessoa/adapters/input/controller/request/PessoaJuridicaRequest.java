package com.rlti.hexagonal.pessoa.adapters.input.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDate;

@Data
public class PessoaJuridicaRequest {
    @CNPJ(message = "CNPJ inválido")
   // @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$", message = "CNPJ deve estar no formato 99.999.999/9999-99")
    private String cnpj;
    private LocalDate dataAbertura;
    private String razaoSocial;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String situacao;

}
