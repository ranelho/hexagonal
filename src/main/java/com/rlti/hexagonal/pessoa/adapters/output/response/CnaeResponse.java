package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.Cnae;
import lombok.Data;

import java.util.List;

@Data
public class CnaeResponse {

    private Long id;
    private String subclasse;
    private String denominacao;

    public CnaeResponse(Cnae cnae) {
        this.id = cnae.getId();
        this.subclasse = cnae.getSubclasse();
        this.denominacao = cnae.getDenominacao();
    }

    public static List<CnaeResponse> convert(List<Cnae> cnaes) {
        return cnaes.stream().map(CnaeResponse::new).toList();
    }
}
