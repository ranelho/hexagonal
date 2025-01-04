package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Endereco;

import java.util.List;

public record EnderecoResponse(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf) {

    public EnderecoResponse(Endereco endereco) {
        this(
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getMunicipio(),
                endereco.getUf()
        );
    }

    public static List<EnderecoResponse> converte(List<Endereco> enderecos) {
        return enderecos.stream()
                .map(EnderecoResponse::new)
                .toList();
    }
}