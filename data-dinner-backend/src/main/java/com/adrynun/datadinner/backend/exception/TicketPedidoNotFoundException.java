package com.adrynun.datadinner.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando no se encuentra un ticket de pedido por su ID.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TicketPedidoNotFoundException extends RuntimeException {
    public TicketPedidoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
