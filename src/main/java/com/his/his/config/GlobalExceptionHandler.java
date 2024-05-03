package com.his.his.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        if ("/api/v1/auth/authenticate".equals(request.getRequestURI())) {
            return new ResponseEntity<>("Access Denied: Employee is not active.", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>("Access Denied: You do not have the necessary permissions.", HttpStatus.FORBIDDEN);
        }
    }
}