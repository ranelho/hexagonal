package com.rlti.hexagonal.pessoa.adapters.input.request;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoEndereco;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoLogradouro;
import lombok.Data;

@Data
public class EnderecoRequest {
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String cidade;
    private String uf;
    private TipoEndereco tipoEndereco;
    private TipoLogradouro tipoLogradouro;
    private boolean indicadorCorrespondencia;
    private boolean indicadorFaturamento;
}
