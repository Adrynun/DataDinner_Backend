package com.adrynun.datadinner.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para la entidad PedidoProducto.
 * Permite enviar información de productos en un pedido al frontend.
 */
public class PedidoProductoDTO {

    private Integer id;

    @NotNull(message = "El producto es obligatorio")
    private Integer productoId;

    @NotNull(message = "El pedido es obligatorio")
    private Integer pedidoId;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidad;

    public PedidoProductoDTO() {}

    public PedidoProductoDTO(Integer id, Integer productoId, Integer pedidoId, int cantidad) {
        this.id = id;
        this.productoId = productoId;
        this.pedidoId = pedidoId;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
