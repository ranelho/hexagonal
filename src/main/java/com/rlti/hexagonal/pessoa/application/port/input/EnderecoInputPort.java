package com.rlti.hexagonal.pessoa.application.port.input;

import com.rlti.hexagonal.pessoa.adapters.input.request.EnderecoRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.EnderecoResponse;

import java.util.List;

public interface EnderecoInputPort {
    EnderecoResponse buscarEndereco(String cep);

    void salvarEndereco(Long idPessoa, EnderecoRequest endereco);

    List<String> buscarNomesMunicipiosPorUF(String uf);
}
