package com.adrynun.datadinner.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando no se encuentra una mesa por su ID.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MesaNotFoundException extends RuntimeException {
    public MesaNotFoundException(String mensaje) {
        super(mensaje);
    }
}
