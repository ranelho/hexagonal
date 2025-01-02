package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pais;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.PessoaFisica;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.EstadoCivil;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.GrauDeInstrucao;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
public class PessoaFisicaDetalheResponse {
    private Long id;
    private String cpf;
    private String nome;
    private Tipo tipo;
    private LocalDate dataNascimento;
    private LocalDate dataObito;
    private String sexo;
    private String nacionalidade;
    private String estadoCivil;
    private String nomePai;
    private String nomeMae;
    private String estrangeiro ;
    private String naturalidade;
    private BigDecimal rendaMensal;
    private boolean indicadorFacta ;
    private boolean indicadoCrs;
    private boolean indicadorPpe ;

    private GrauDeInstrucao grauDeInstrucao;
    private List<TelefoneResponse> telefones;
    private List<EmailResponse> emails;
    private List<EnderecoResponse> enderecos;
    private AutoDeclaracaoResponse autoDeclaracao;
    private List<ContaBancariaResponse> contaBancaria;

    public PessoaFisicaDetalheResponse(PessoaFisica pessoaFisica) {
        this.id = pessoaFisica.getIdPessoaFisica();
        this.cpf = pessoaFisica.getCpf();
        this.nome = pessoaFisica.getPessoa().getNome();
        this.tipo = pessoaFisica.getPessoa().getTipo();
        this.dataNascimento = pessoaFisica.getDataNascimento();
        this.sexo = pessoaFisica.getSexo().toString();
        this.nomePai = pessoaFisica.getNomePai();
        this.nomeMae = pessoaFisica.getNomeMae();
        this.estrangeiro = pessoaFisica.getEstrangeiro();
        this.naturalidade = pessoaFisica.getNaturalidade();
        this.rendaMensal = pessoaFisica.getRendaMensal();
        this.indicadorFacta = pessoaFisica.isIndicadorFacta();
        this.indicadoCrs = pessoaFisica.isIndicadoCrs();
        this.indicadorPpe = pessoaFisica.isIndicadorPpe();
        this.grauDeInstrucao = pessoaFisica.getGrauDeInstrucao();

        this.nacionalidade = Optional.ofNullable(pessoaFisica.getPais())
                .map(Pais::getNome)
                .orElse(null);

        this.estadoCivil = Optional.ofNullable(pessoaFisica.getEstadoCivil())
                .map(EstadoCivil::getNomeEstadoCivil)
                .orElse(null);

        this.telefones = List.copyOf(
                Optional.ofNullable(pessoaFisica.getPessoa().getTelefones())
                        .map(TelefoneResponse::converte)
                        .orElse(Collections.emptyList())
        );

        this.emails = List.copyOf(
                Optional.ofNullable(pessoaFisica.getPessoa().getEmails())
                        .map(EmailResponse::converte)
                        .orElse(Collections.emptyList())
        );

        this.enderecos = List.copyOf(
                Optional.ofNullable(pessoaFisica.getPessoa().getEnderecos())
                        .map(EnderecoResponse::converte)
                        .orElse(Collections.emptyList())
        );

        this.contaBancaria = List.copyOf(
                Optional.ofNullable(pessoaFisica.getPessoa().getContas())
                        .map(ContaBancariaResponse::converte)
                        .orElse(Collections.emptyList())
        );

        this.autoDeclaracao = new AutoDeclaracaoResponse(
                pessoaFisica.getEspecificacaoPpe(),
                pessoaFisica.getEspecificacaoIg(),
                pessoaFisica.getEspecificacaoOs(),
                pessoaFisica.getCorRaca().getCorRaca()
        );
    }

    public static Page<PessoaFisicaDetalheResponse> converte(Page<PessoaFisica> pessoas) {
        return pessoas.map(PessoaFisicaDetalheResponse::new);
    }
}
