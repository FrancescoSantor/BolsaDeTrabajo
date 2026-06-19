package com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
