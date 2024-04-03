package com.his.his.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor; 
import org.springframework.core.env.Environment; 
  
import jakarta.persistence.AttributeConverter; 
import jakarta.persistence.Converter; 

@Converter
public class ObjectCryptoConverter implements AttributeConverter<Object, String> {

    private static final String ENCRYPTION_PASSWORD_PROPERTY = "jasypt.encryptor.password";
    private final StandardPBEStringEncryptor encryptor;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ObjectCryptoConverter(Environment environment) {
        this.encryptor = new StandardPBEStringEncryptor();
        this.encryptor.setPassword(environment.getProperty(ENCRYPTION_PASSWORD_PROPERTY));
    }

    @Override
    public String convertToDatabaseColumn(Object attribute) {
        try {
            String json = objectMapper.writeValueAsString(attribute);
            return encryptor.encrypt(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        try {
            String json = encryptor.decrypt(dbData);
            return objectMapper.readValue(json, Object.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}