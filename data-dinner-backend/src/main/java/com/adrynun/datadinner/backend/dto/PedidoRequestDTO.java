package com.adrynun.datadinner.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * DTO usado para **CREAR** un pedido desde cero.
 * Requiere la mesa, el usuario y los productos iniciales.
 * CRÍTICO: Utiliza PedidoProductoRequestDTO que solo contiene productoId y
 * cantidad.
 */
public class PedidoRequestDTO {

    @NotNull(message = "La mesa es obligatoria")
    private Integer mesaId;

    @NotNull(message = "El usuario es obligatorio")
    private Integer usuarioId;

    @NotNull(message = "Debe incluir al menos un producto")
    @NotEmpty(message = "La lista de productos no puede estar vacía")
    // Se cambia PedidoProductoDTO por la versión de Request
    private List<PedidoProductoRequestDTO> productos;

    public PedidoRequestDTO() {
    }

    public PedidoRequestDTO(Integer mesaId, Integer usuarioId, List<PedidoProductoRequestDTO> productos) {
        this.mesaId = mesaId;
        this.usuarioId = usuarioId;
        this.productos = productos;
    }

    // Getters y Setters
    public Integer getMesaId() {
        return mesaId;
    }

    public void setMesaId(Integer mesaId) {
        this.mesaId = mesaId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<PedidoProductoRequestDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<PedidoProductoRequestDTO> productos) {
        this.productos = productos;
    }
}