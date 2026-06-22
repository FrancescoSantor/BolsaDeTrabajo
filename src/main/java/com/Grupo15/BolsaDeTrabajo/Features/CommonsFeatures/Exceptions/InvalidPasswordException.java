package com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions;


public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
