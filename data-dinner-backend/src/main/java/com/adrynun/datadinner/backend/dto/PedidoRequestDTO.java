package com.adrynun.datadinner.backend.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO usado para recibir datos de un pedido desde el frontend.
 */
public class PedidoRequestDTO {

    @NotNull(message = "La mesa es obligatoria")
    private Integer mesaId;

    @NotNull(message = "El usuario es obligatorio")
    private Integer usuarioId;

    @NotNull(message = "Debe incluir al menos un producto")
    private List<PedidoProductoDTO> productos;

    public PedidoRequestDTO() {}

    public PedidoRequestDTO(Integer mesaId, Integer usuarioId, List<PedidoProductoDTO> productos) {
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

    public List<PedidoProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<PedidoProductoDTO> productos) {
        this.productos = productos;
    }
}
