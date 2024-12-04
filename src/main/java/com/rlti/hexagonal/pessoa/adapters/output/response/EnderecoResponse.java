package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.Endereco;
import lombok.Data;

import java.util.List;

@Data
public class EnderecoResponse {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    public EnderecoResponse(Endereco endereco) {
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.localidade = endereco.getCidade();
        this.uf = endereco.getUf();
    }

    public static List<EnderecoResponse> converte(List<Endereco> enderecos) {
        return enderecos.stream().map(EnderecoResponse::new).toList();
    }
}