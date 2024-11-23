package com.rlti.hexagonal.adapters.input.controller.request;

import lombok.Value;
import org.hibernate.validator.constraints.br.CNPJ;

@Value
public class PessoaJuridicaRequest {
    @CNPJ(message = "CNPJ inválido")
    String cnpj;
    String razaoSocial;
}
