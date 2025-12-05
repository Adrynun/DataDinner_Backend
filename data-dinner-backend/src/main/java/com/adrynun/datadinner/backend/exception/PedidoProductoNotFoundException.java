package com.adrynun.datadinner.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando un PedidoProducto no es encontrado en la base de datos.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PedidoProductoNotFoundException extends RuntimeException {

    /**
     * Constructor con mensaje por defecto usando el ID.
     * @param id ID del PedidoProducto que no se encontró
     */
    public PedidoProductoNotFoundException(int id) {
        super("PedidoProducto con id " + id + " no encontrado");
    }

    /**
     * Constructor con mensaje personalizado.
     * @param message mensaje de error
     */
    public PedidoProductoNotFoundException(String message) {
        super(message);
    }
}
