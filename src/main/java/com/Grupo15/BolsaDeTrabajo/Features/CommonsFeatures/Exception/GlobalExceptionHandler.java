package com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ContraseniaInvalidaException.class)
    public ResponseEntity<String> handleContraseniaInvalida(ContraseniaInvalidaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MailExistenteException.class)
    public ResponseEntity<String> handleMailExistente(MailExistenteException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
