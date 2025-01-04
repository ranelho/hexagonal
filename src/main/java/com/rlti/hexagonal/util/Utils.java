package com.rlti.hexagonal.util;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.Sexo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.LongFunction;
import java.util.function.Supplier;

public class Utils {

    private Utils() {
        throw new UnsupportedOperationException("Util class cannot be instantiated");
    }

    public static <T> T findEntityById(Long id, LongFunction<Optional<T>> repositoryFindById, Supplier<RuntimeException> exceptionSupplier) {
        return id != null ?
                repositoryFindById.apply(id)
                        .orElseThrow(exceptionSupplier)
                : null;
    }

    /**
     * Formata uma data de nascimento para o formato "yyyy-MM-dd".
     * Se a data for null, retorna null.
     *
     * @param dataNascimento A data de nascimento a ser formatada.
     * @return A data formatada ou null se a data for null.
     */
    public static String formatDataNascimento(LocalDate dataNascimento) {
        return Optional.ofNullable(dataNascimento)
                .map(d -> d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .orElse(null);
    }

    /**
     * Converte um valor do tipo Sexo para o seu nome em formato de String.
     * Se o valor for null, retorna null.
     *
     * @param sexo O valor do sexo a ser convertido.
     * @return O nome do sexo como String ou null se o valor for null.
     */
    public static <E extends Enum<E>> String formatEnum(E enumValue) {
        return Optional.ofNullable(enumValue)
                .map(Enum::name)
                .orElse(null);
    }

    /**
     * Método genérico que formata um valor de String baseado no parâmetro recebido.
     * Caso o parâmetro seja null, retorna null.
     *
     * @param value O valor a ser formatado.
     * @param formatter O formato para a conversão.
     * @return O valor formatado ou null se o valor for null.
     */
    public static String formatString(String value, DateTimeFormatter formatter) {
        return Optional.ofNullable(value)
                .map(val -> val.format(String.valueOf(formatter)))
                .orElse(null);
    }
}
