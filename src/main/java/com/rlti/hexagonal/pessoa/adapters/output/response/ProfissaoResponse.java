package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.Profissao;

import java.util.List;

public record ProfissaoResponse(
        String codigo,
        String nomeProfissao) {

    public ProfissaoResponse(Profissao profissao) {
        this(
                profissao.getNumeroProfissao(),
                profissao.getNomeProfissao()
        );
    }

    public static List<ProfissaoResponse> convert(List<Profissao> profissoes) {
        return profissoes.stream()
                .map(ProfissaoResponse::new)
                .toList();
    }
}