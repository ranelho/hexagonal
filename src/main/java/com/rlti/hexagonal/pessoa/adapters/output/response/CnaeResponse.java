package com.rlti.hexagonal.pessoa.adapters.output.response;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.Cnae;

import java.util.List;

public record CnaeResponse(
        Long id,
        String subclasse,
        String denominacao,
        boolean isPrincipal) {

    public CnaeResponse(Cnae cnae) {
        this(
                cnae.getId(),
                cnae.getSubclasse(),
                cnae.getDenominacao(),
                cnae.isPrincipal()
        );
    }

    public static List<CnaeResponse> convert(List<Cnae> cnaes) {
        return cnaes.stream()
                .map(CnaeResponse::new)
                .toList();
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
