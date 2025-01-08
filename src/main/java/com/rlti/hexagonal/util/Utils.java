package com.rlti.hexagonal.util;

import com.rlti.hexagonal.handler.EntidadeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.LongFunction;

public class Utils {

    private Utils() {
        throw new UnsupportedOperationException("Util class cannot be instantiated");
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
                .map(val -> String.format(String.valueOf(formatter)))
                .orElse(null);
    }

    public static <T> T findEntity(long id, LongFunction<Optional<T>> repository, String entidadeNome) {
        return repository.apply(id)
                .orElseThrow(() -> new EntidadeException(entidadeNome + " não encontrada com ID: " + id));
    }

    public static <T, R> T fetchEntity(R entity, Function<R, Long> idExtractor, LongFunction<Optional<T>> repository, String entityName) {
        return Optional.ofNullable(entity)
                .map(idExtractor)
                .map(id -> findEntity(id, repository, entityName))
                .orElseThrow(() -> new EntidadeException(entityName + " não pode ser nula"));
    }







}
