package com.rlti.hexagonal.pessoa.config;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true) // Aplica o conversor automaticamente a todas as entidades que usam enums
public class PostgreSQLEnumConverter<E extends Enum<E>> implements AttributeConverter<E, String> {

    private final Class<E> enumClass;

    public PostgreSQLEnumConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(E attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name(); // Converte o enum para seu nome como string
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Enum.valueOf(enumClass, dbData); // Converte a string do banco de dados para o enum correspondente
    }
}
