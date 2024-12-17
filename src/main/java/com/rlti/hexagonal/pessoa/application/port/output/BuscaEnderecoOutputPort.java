package com.rlti.hexagonal.pessoa.application.port.output;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Endereco;

import java.util.List;

public interface BuscaEnderecoOutputPort {
    Endereco buscarEndereco(String cep);

    List<String> buscarNomesMunicipiosPorUF(String uf);
}
