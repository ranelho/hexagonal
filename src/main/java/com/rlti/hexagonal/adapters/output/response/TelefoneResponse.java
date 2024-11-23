package com.rlti.hexagonal.adapters.output.response;

import com.rlti.hexagonal.adapters.output.repository.entity.TipoTelefone;
import com.rlti.hexagonal.application.core.domain.Telefone;
import lombok.Data;

import java.util.List;

@Data
public class TelefoneResponse {
    private Long id;
    private TipoTelefone  tipoTelefone;
    private String ddi;
    private String ddd;
    private String numeroTelefone;

    public TelefoneResponse(Telefone telefone) {
        this.id = telefone.getId();
        this.tipoTelefone = telefone.getTipoTelefone();
        this.ddi = telefone.getDdi();
        this.ddd = telefone.getDdd();
        this.numeroTelefone = telefone.getNumeroTelefone();
    }

    public static List<TelefoneResponse> converte(List<Telefone> telefones) {
        return telefones.stream()
                .map(TelefoneResponse::new)
                .toList();
    }
}
