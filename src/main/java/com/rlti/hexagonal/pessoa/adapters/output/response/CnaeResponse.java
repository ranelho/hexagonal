package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.Cnae;
import lombok.Data;

import java.util.List;

@Data
public class CnaeResponse {

    private Long id;
    private String subclasse;
    private String denominacao;
    boolean isPrincipal;

    public CnaeResponse(Cnae cnae) {
        this.id = cnae.getId();
        this.subclasse = cnae.getSubclasse();
        this.denominacao = cnae.getDenominacao();
        this.isPrincipal = cnae.isPrincipal();
    }

    public static List<CnaeResponse> convert(List<Cnae> cnaes) {
        return cnaes.stream().map(CnaeResponse::new).toList();
    }

    public static CnaeResponse getPrincipal(List<Cnae> cnaes) {
        return cnaes.stream()
                .filter(Cnae::isPrincipal)
                .map(CnaeResponse::new)
                .findFirst()
                .orElse(null);
    }

    public static List<CnaeResponse> getSecundarios(List<Cnae> cnaes) {
        return cnaes.stream()
                .filter(cnae -> !cnae.isPrincipal())
                .map(CnaeResponse::new)
                .toList();
    }
}
