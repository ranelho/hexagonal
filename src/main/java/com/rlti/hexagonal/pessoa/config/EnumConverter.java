package com.rlti.hexagonal.pessoa.config;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EnumConverter<T extends Enum<T>> implements AttributeConverter<T, String> {

    private final Class<T> enumType;

    // Recebe a classe do enum como parâmetro no construtor
    public EnumConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return attribute != null ? attribute.name() : null;
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            // Usando o tipo do enum para fazer a conversão de volta
            return Enum.valueOf(enumType, dbData);
        } catch (IllegalArgumentException e) {
            // Se não for um valor válido para o enum, você pode tratar isso
            return null;
        }
    }
}


