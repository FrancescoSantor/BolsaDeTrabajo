package com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions;

public class InactiveUserException extends RuntimeException {
    public InactiveUserException(String message) {
        super(message);
    }
}
