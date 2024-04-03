package com.his.his.converter;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor; 
import org.springframework.core.env.Environment; 
  
import jakarta.persistence.AttributeConverter; 
import jakarta.persistence.Converter; 
  
/** 
 * JPA Attribute Converter for encrypting and decrypting String attributes. This 
 * converter uses the Jasypt library for encryption and decryption operations. 
 * It is configured with an environment variable for the encryption password. 
 *  
 * Note: Ensure that the Jasypt library is correctly configured in your project. 
 * The encryption password should be provided through the 
 * "jasypt.encryptor.password" property. 
 *  
 * @author rahul.chauhan 
 */
@Converter
public class StringCryptoConverter implements AttributeConverter<String, String> { 
  
    // Property name for the encryption password 
    private static final String ENCRYPTION_PASSWORD_PROPERTY = "jasypt.encryptor.password"; 
  
    // Jasypt StringEncryptor for performing encryption and decryption 
    private final StandardPBEStringEncryptor encryptor; 
  
    /** 
     * Constructor for StringCryptoConverter. 
     *  
     * @param environment The Spring Environment used to access properties. 
     */
    public StringCryptoConverter(Environment environment) { 
        // Initialize the encryptor with the encryption password from the environment 
        this.encryptor = new StandardPBEStringEncryptor(); 
        this.encryptor.setPassword(environment.getProperty(ENCRYPTION_PASSWORD_PROPERTY)); 
        // this.encryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
    } 
  
    /** 
     * Converts the attribute value to the encrypted form. 
     *  
     * @param attribute The original attribute value to be encrypted. 
     * @return The encrypted form of the attribute. 
     */
    @Override
    public String convertToDatabaseColumn(String attribute) { 
        try {
            return this.encryptor.encrypt(attribute); 
        } catch (Exception e) {
            System.out.println(e);
            return e.getMessage();
        }
    } 
  
    /** 
     * Converts the encrypted database value to its decrypted form. 
     *  
     * @param dbData The encrypted value stored in the database. 
     * @return The decrypted form of the database value. 
     */
    @Override
    public String convertToEntityAttribute(String dbData) { 
        try {
            // System.out.println("dbData: " + dbData);
            return this.encryptor.decrypt(dbData);   
        } catch (Exception e) {
            System.out.println(e);
            return e.getMessage();
        } 
    } 
} 