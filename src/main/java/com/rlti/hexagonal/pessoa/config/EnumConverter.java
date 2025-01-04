package com.rlti.hexagonal.pessoa.config;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EnumConverter<T extends Enum<T>> implements AttributeConverter<T, String> {

    private final Class<T> enumType;

    public EnumConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return attribute != null ? attribute.name() : null;
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        return dbData != null ? Enum.valueOf(enumType, dbData) : null;
    }
}
