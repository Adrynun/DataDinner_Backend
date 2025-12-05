package com.adrynun.datadinner.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DTO para la entidad PedidoProducto.
 * Permite **enviar** información de productos en un pedido al frontend
 * (Response).
 * Incluye el precio unitario guardado en la DB para el cálculo en el frontend.
 */
public class PedidoProductoResponseDTO {

    private Integer id; // ID de la línea (para posible edición)

    @NotNull(message = "El producto es obligatorio")
    private Integer productoId;

    @NotNull(message = "El pedido es obligatorio")
    private Integer pedidoId;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidad;

    private BigDecimal precioUnitario; // CRÍTICO: Precio histórico para mostrar y calcular

    private String nombreProducto; // Nombre del producto para el detalle

    // Constructores
    public PedidoProductoResponseDTO() {
    }

    public PedidoProductoResponseDTO(Integer id, Integer productoId, Integer pedidoId, int cantidad,
            BigDecimal precioUnitario, String nombreProducto) {
        this.id = id;
        this.productoId = productoId;
        this.pedidoId = pedidoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.nombreProducto = nombreProducto;
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

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}