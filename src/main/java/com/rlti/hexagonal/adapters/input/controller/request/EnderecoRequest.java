package com.rlti.hexagonal.adapters.input.controller.request;

import com.rlti.hexagonal.application.core.domain.enums.TipoEndereco;
import lombok.Data;

@Data
public class EnderecoRequest {
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private TipoEndereco tipoEndereco;
    private String indicadorCorrespondencia;
    private String indicadorFaturamento;
}
