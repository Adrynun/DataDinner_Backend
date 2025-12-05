package com.adrynun.datadinner.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando no se encuentra un cierre de caja por fecha.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CierreCajaNotFoundException extends RuntimeException {
    public CierreCajaNotFoundException(String mensaje) {
        super(mensaje);
    }
}
