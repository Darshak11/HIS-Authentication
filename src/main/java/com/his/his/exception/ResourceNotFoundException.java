package com.his.his.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    // private static final Logger logger = LogManager.getLogger(ResourceNotFoundException.class);
    public ResourceNotFoundException(String message){
        
        super(message);
        // logger.warn("Resource Not Found Exception");
        // logger.error("This is an error message");
    }
}
