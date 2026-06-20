package com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(String message) {
        super(message);
    }
}
