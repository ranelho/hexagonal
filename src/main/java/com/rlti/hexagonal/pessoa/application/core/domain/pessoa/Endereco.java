package com.rlti.hexagonal.pessoa.application.core.domain.pessoa;

import com.rlti.hexagonal.pessoa.adapters.input.request.EnderecoRequest;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoEndereco;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoLogradouro;
import lombok.Data;

@Data //todo - remove lombok
public class Endereco {
    private Long idEndereco;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private TipoEndereco tipoEndereco;
    private TipoLogradouro tipoLogradouro;
    private boolean indicadorCorrespondencia;
    private boolean indicadorFaturamento;

    public Endereco() {
    }

    public Endereco(EnderecoRequest enderecoRequest) {
        this.cep = enderecoRequest.getCep();
        this.logradouro = enderecoRequest.getLogradouro();
        this.numero = enderecoRequest.getNumero();
        this.complemento = enderecoRequest.getComplemento();
        this.bairro = enderecoRequest.getBairro();
        this.localidade = enderecoRequest.getLocalidade();
        this.uf = enderecoRequest.getUf();
        this.tipoEndereco = enderecoRequest.getTipoEndereco();
        this.indicadorCorrespondencia = enderecoRequest.isIndicadorCorrespondencia();
        this.indicadorFaturamento = enderecoRequest.isIndicadorFaturamento();
        this.tipoLogradouro = enderecoRequest.getTipoLogradouro();
    }


}
