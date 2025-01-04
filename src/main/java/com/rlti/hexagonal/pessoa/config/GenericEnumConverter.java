package com.rlti.hexagonal.pessoa.config;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenericEnumConverter<E extends Enum<E>> implements AttributeConverter<E, String> {

    private Class<E> enumClass;

    @SuppressWarnings("unchecked")
    private void initEnumClass(E attribute) {
        if (enumClass == null && attribute != null) {
            this.enumClass = (Class<E>) attribute.getClass();
        }
    }

    @Override
    public String convertToDatabaseColumn(E attribute) {
        initEnumClass(attribute);
        return attribute != null ? attribute.name() : null;
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        if (dbData == null || enumClass == null) {
            return null;
        }
        return Enum.valueOf(enumClass, dbData);
    }
}
