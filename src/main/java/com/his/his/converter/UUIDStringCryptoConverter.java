package com.his.his.converter;

import java.util.UUID;

import org.springframework.core.env.Environment;

import jakarta.persistence.AttributeConverter; 
public class UUIDStringCryptoConverter implements AttributeConverter<UUID, String>{
    private final StringCryptoConverter stringCryptoConverter;

    public UUIDStringCryptoConverter(Environment environment) {
        this.stringCryptoConverter = new StringCryptoConverter(environment);
    }

    @Override
    public String convertToDatabaseColumn(UUID attribute) {
        if (attribute == null) {
            return null;
        }
        return stringCryptoConverter.convertToDatabaseColumn(attribute.toString());
    }

    @Override
    public UUID convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return UUID.fromString(stringCryptoConverter.convertToEntityAttribute(dbData));
    }
}
