package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.PessoaJuridica;
import lombok.Data;

import java.util.List;

@Data
public class PessoaJuridicaDetalheResponse {
    private Long id;
    private String cnpj;
    private String nome;
    private Tipo tipo;
    private String razaoSocial;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String situacao;
    private List<EnderecoResponse> enderecos;
    private CnaeResponse cnaePrincipal;
    private List<CnaeResponse> cnaesSecundarios;

    public PessoaJuridicaDetalheResponse(PessoaJuridica pessoaJuridica) {
        this.id = pessoaJuridica.getPessoa().getIdPessoa();
        this.cnpj = pessoaJuridica.getCnpj();
        this.nome = pessoaJuridica.getPessoa().getNome();
        this.tipo = pessoaJuridica.getPessoa().getTipo();
        this.razaoSocial = pessoaJuridica.getRazaoSocial();
        this.nomeFantasia = pessoaJuridica.getNomeFantasia();
        this.inscricaoEstadual = pessoaJuridica.getInscricaoEstadual();
        this.inscricaoMunicipal = pessoaJuridica.getInscricaoMunicipal();
        this.situacao = pessoaJuridica.getSituacao();
        this.enderecos = EnderecoResponse.converte(pessoaJuridica.getPessoa().getEnderecos());
        this.cnaePrincipal = CnaeResponse.getPrincipal(pessoaJuridica.getCnaes());
        this.cnaesSecundarios = CnaeResponse.getSecundarios(pessoaJuridica.getCnaes());
    }
}
