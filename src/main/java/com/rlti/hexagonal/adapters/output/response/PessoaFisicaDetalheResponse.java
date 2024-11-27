package com.rlti.hexagonal.adapters.output.response;

import com.rlti.hexagonal.application.core.domain.PessoaFisica;
import com.rlti.hexagonal.application.core.domain.enums.EstadoCivil;
import com.rlti.hexagonal.application.core.domain.enums.GrauDeInstrucao;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class PessoaFisicaDetalheResponse {
    private Long id;
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private LocalDate dataObito;
    private String sexo;
    private String nacionalidade;
    private EstadoCivil estadoCivil;
    private String nomePai;
    private String nomeMae;
    private String estrangeiro ;
    private String naturalidade;
    private BigDecimal rendaMensal;
    private String indicadorFacta ;
    private String indicadoCrs;
    private String indicadorPpe ;
    private String especificacaoPpe;
    private String especificacaoIg;
    private String especificacaoOs;
    private GrauDeInstrucao grauDeInstrucao;
    private List<TelefoneResponse> telefones;
    private List<EmailResponse> emails;


    public PessoaFisicaDetalheResponse(PessoaFisica pessoaFisica) {
        this.id = pessoaFisica.getIdPessoaFisica()                                   ;
        this.cpf = pessoaFisica.getCpf();
        this.nome = pessoaFisica.getPessoa().getNome();
        this.dataNascimento = pessoaFisica.getDataNascimento();
        this.sexo = pessoaFisica.getSexo().toString();
        this.nacionalidade = pessoaFisica.getPais().getNacionalidade();
        this.estadoCivil = pessoaFisica.getEstadoCivil();
        this.nomePai = pessoaFisica.getNomePai();
        this.nomeMae = pessoaFisica.getNomeMae();
        this.estrangeiro = pessoaFisica.getEstrangeiro();
        this.naturalidade = pessoaFisica.getNaturalidade();
        this.rendaMensal = pessoaFisica.getRendaMensal();
        this.indicadorFacta = pessoaFisica.getIndicadorFacta();
        this.indicadoCrs = pessoaFisica.getIndicadoCrs();
        this.indicadorPpe = pessoaFisica.getIndicadorPpe();
        this.especificacaoPpe = pessoaFisica.getEspecificacaoPpe();
        this.especificacaoIg = pessoaFisica.getEspecificacaoIg();
        this.especificacaoOs = pessoaFisica.getEspecificacaoOs();
        this.grauDeInstrucao = pessoaFisica.getGrauDeInstrucao();
        this.telefones = TelefoneResponse.converte(pessoaFisica.getTelefones());
        this.emails = EmailResponse.converte(pessoaFisica.getEmails());
    }

    public static Page<PessoaFisicaDetalheResponse> converte(Page<PessoaFisica> pessoas) {
        return pessoas.map(PessoaFisicaDetalheResponse::new);
    }
}
