package com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions;

public class InvalidSalaryRangeException extends RuntimeException {
    public InvalidSalaryRangeException(String message) {
        super(message);
    }
}
