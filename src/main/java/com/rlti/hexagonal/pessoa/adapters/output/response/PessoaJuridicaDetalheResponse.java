package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.PessoaJuridica;
import lombok.Data;

import java.util.List;

public record PessoaJuridicaDetalheResponse(
        Long id,
        String cnpj,
        String nome,
        Tipo tipo,
        String razaoSocial,
        String nomeFantasia,
        String inscricaoEstadual,
        String inscricaoMunicipal,
        String situacao,
        List<EnderecoResponse> enderecos,
        CnaeResponse cnaePrincipal,
        List<CnaeResponse> cnaesSecundarios
) {
    public PessoaJuridicaDetalheResponse(PessoaJuridica pessoaJuridica) {
        this(
                pessoaJuridica.getPessoa().getIdPessoa(),
                pessoaJuridica.getCnpj(),
                pessoaJuridica.getPessoa().getNome(),
                pessoaJuridica.getPessoa().getTipo(),
                pessoaJuridica.getRazaoSocial(),
                pessoaJuridica.getNomeFantasia(),
                pessoaJuridica.getInscricaoEstadual(),
                pessoaJuridica.getInscricaoMunicipal(),
                pessoaJuridica.getSituacao(),
                EnderecoResponse.converte(pessoaJuridica.getPessoa().getEnderecos()),
                CnaeResponse.getPrincipal(pessoaJuridica.getCnaes()),
                CnaeResponse.getSecundarios(pessoaJuridica.getCnaes())
        );
    }
}

