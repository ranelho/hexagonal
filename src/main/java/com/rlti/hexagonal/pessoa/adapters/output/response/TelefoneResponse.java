package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoTelefone;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Telefone;

import java.util.List;

public record TelefoneResponse(
        Long id,
        TipoTelefone tipoTelefone,
        String ddi,
        String ddd,
        String numeroTelefone
) {
    public TelefoneResponse(Telefone telefone) {
        this(
                telefone.getId(),
                telefone.getTipoTelefone(),
                telefone.getDdi(),
                telefone.getDdd(),
                telefone.getNumeroTelefone()
        );
    }

    public static List<TelefoneResponse> converte(List<Telefone> telefones) {
        return telefones.stream()
                .map(TelefoneResponse::new)
                .toList();
    }
}
