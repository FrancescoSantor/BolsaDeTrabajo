package com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions;

public class ExistingEmailException extends RuntimeException {
    public ExistingEmailException(String message) {
        super(message);
    }
}
