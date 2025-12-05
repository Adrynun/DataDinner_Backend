package com.adrynun.datadinner.backend.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO usado para actualizar un pedido existente.
 * Contiene la lista de productos que se desea añadir o modificar.
 */
public class ActualizarPedidoRequestDTO {

    /** * El frontend solo necesita enviar los items que se deben añadir o modificar.
     * La lógica de actualización debe manejar si un item ya existe (sumar cantidad)
     * o si es nuevo (crear PedidoProducto).
     */
    @NotNull(message = "Debe incluir al menos un producto para actualizar")
    private List<PedidoProductoRequestDTO> productos;

    public ActualizarPedidoRequestDTO() {}

    public ActualizarPedidoRequestDTO(List<PedidoProductoRequestDTO> productos) {
        this.productos = productos;
    }

    // Getters y Setters
    public List<PedidoProductoRequestDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<PedidoProductoRequestDTO> productos) {
        this.productos = productos;
    }
}