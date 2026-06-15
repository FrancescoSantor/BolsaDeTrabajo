package com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exception;

public class MailExistenteException extends RuntimeException {
    public MailExistenteException(String message) {
        super(message);
    }
}
