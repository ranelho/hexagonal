package com.rlti.hexagonal.util;

import java.util.List;
import java.util.Map;

public class SortFieldResolver {
    public static final String PESSOA_NOME = "Pessoa.nome";
    private static final Map<String, List<String>> allowedSortFields = Map.of(
            "pessoaFisica", List.of("idPessoa", PESSOA_NOME, "cpf"),
            "pessoaJuridica", List.of("idPessoa", PESSOA_NOME, "cnpj")
    );

    private static final Map<String, String> defaultSortFields = Map.of(
            "pessoa", PESSOA_NOME,
            "categoria", PESSOA_NOME
    );

    // Construtor privado para impedir instância
    private SortFieldResolver() {
        throw new UnsupportedOperationException("Esta classe não pode ser instanciada.");
    }

    public static boolean isValidSortField(String context, String sortBy) {
        List<String> allowedFields = allowedSortFields.getOrDefault(context, List.of());
        return !allowedFields.contains(sortBy);
    }

    public static String getDefaultSortField(String context) {
        return defaultSortFields.getOrDefault(context, "id");
    }
}
