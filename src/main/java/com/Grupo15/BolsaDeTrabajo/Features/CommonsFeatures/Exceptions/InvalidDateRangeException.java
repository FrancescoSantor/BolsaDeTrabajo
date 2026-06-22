package com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions;

public class InvalidDateRangeException extends RuntimeException {
    public InvalidDateRangeException(String message) {
        super(message);
    }
}
