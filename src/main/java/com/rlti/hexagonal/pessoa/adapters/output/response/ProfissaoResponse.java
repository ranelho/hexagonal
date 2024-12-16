package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.Profissao;
import lombok.Data;

import java.util.List;

@Data
public class ProfissaoResponse {
    private Long codigo;
    private String nomeProfissao;

    public ProfissaoResponse(Profissao profissao) {
        this.codigo = profissao.getCodigo();
        this.nomeProfissao = profissao.getNomeProfissao();
    }

    public static List<ProfissaoResponse> convert(List<Profissao> profissoes){
        return profissoes.stream().map(ProfissaoResponse::new).toList();
    }
}
