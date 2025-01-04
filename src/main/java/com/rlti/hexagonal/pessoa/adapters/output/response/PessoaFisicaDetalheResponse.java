package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pais;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.*;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record PessoaFisicaDetalheResponse(
        Long id,
        String cpf,
        String nome,
        Tipo tipo,
        LocalDate dataNascimento,
        LocalDate dataObito,
        String sexo,
        String nacionalidade,
        String estadoCivil,
        String nomePai,
        String nomeMae,
        String estrangeiro,
        String naturalidade,
        BigDecimal rendaMensal,
        boolean indicadorFacta,
        boolean indicadoCrs,
        boolean indicadorPpe,
        String especificacaoPpe,
        GrauInstrucao grauInstrucao,
        List<TelefoneResponse> telefones,
        List<EmailResponse> emails,
        List<EnderecoResponse> enderecos,
        List<ContaBancariaResponse> contaBancaria,
        AutoDeclaracaoResponse autoDeclaracao
) {
    public PessoaFisicaDetalheResponse(PessoaFisica pessoaFisica) {
        this(
                pessoaFisica.getIdPessoaFisica(),
                pessoaFisica.getCpf(),
                pessoaFisica.getPessoa().getNome(),
                pessoaFisica.getPessoa().getTipo(),
                pessoaFisica.getDataNascimento(),
                null,
                pessoaFisica.getSexo().toString(),
                Optional.ofNullable(pessoaFisica.getPais())
                        .map(Pais::getNacionalidade).orElse(null),
                Optional.ofNullable(pessoaFisica.getEstadoCivil())
                        .map(EstadoCivil::getNomeEstadoCivil).orElse(null),
                pessoaFisica.getNomePai(),
                pessoaFisica.getNomeMae(),
                pessoaFisica.getEstrangeiro(),
                pessoaFisica.getNaturalidade(),
                pessoaFisica.getRendaMensal(),
                pessoaFisica.isIndicadorFacta(),
                pessoaFisica.isIndicadoCrs(),
                pessoaFisica.isIndicadorPpe(),
                pessoaFisica.getEspecificacaoPpe(),
                pessoaFisica.getGrauInstrucao(),
                List.copyOf(Optional.ofNullable(pessoaFisica.getPessoa().getTelefones())
                        .map(TelefoneResponse::converte).orElse(Collections.emptyList())),
                List.copyOf(Optional.ofNullable(pessoaFisica.getPessoa().getEmails())
                        .map(EmailResponse::converte).orElse(Collections.emptyList())),
                List.copyOf(Optional.ofNullable(pessoaFisica.getPessoa().getEnderecos())
                        .map(EnderecoResponse::converte).orElse(Collections.emptyList())),
                List.copyOf(Optional.ofNullable(pessoaFisica.getPessoa().getContas())
                        .map(ContaBancariaResponse::converte).orElse(Collections.emptyList())),
                new AutoDeclaracaoResponse(
                        Optional.ofNullable(pessoaFisica.getIdentidadeGenero())
                                .map(IdentidadeGenero::getGenero).orElse(null),
                        pessoaFisica.getEspecificacaoIg(),
                        Optional.ofNullable(pessoaFisica.getOrientacaoSexual())
                                .map(OrientacaoSexual::getOrientacaoSexual).orElse(null),
                        pessoaFisica.getEspecificacaoOs(),
                        Optional.ofNullable(pessoaFisica.getCorRaca())
                                .map(CorRaca::getCorRaca).orElse(null)
                )
        );
    }

    public static Page<PessoaFisicaDetalheResponse> converte(Page<PessoaFisica> pessoas) {
        return pessoas.map(PessoaFisicaDetalheResponse::new);
    }
}
