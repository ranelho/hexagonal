package com.rlti.hexagonal.application.core.domain.enums;

public enum TipoNaturezaJuridica {
    ADMINISTRACAO_PUBLICA(1, "Administração Pública"),
    ENTIDADES_EMPRESARIAIS(2, "Entidades Empresariais"),
    ENTIDADES_SEM_FINS_LUCROS(3, "Entidades Sem Fins Lucrativos"),
    PESSOAS_FISICAS(4, "Pessoas Físicas"),
    ORGANIZACOES_INTERNACIONAIS(5, "Organizações Internacionais");

    private final int codigo;
    private final String descricao;

    TipoNaturezaJuridica(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoNaturezaJuridica fromCodigo(int codigo) {
        for (TipoNaturezaJuridica tipo : values()) {
            if (tipo.getCodigo() == codigo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}
