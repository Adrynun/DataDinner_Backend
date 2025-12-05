package com.adrynun.datadinner.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO usado para enviar/recibir una línea de producto (item) dentro de un
 * Pedido.
 * * CRÍTICO: Se eliminan las IDs de Pedido y de la propia línea, ya que estas
 * son generadas o gestionadas por la capa de servicio (PedidoService).
 */
public class PedidoProductoRequestDTO {

    @NotNull(message = "El ID del producto base es obligatorio")
    private Integer productoId;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidad;

    // Constructores
    public PedidoProductoRequestDTO() {
    }

    public PedidoProductoRequestDTO(Integer productoId, int cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}