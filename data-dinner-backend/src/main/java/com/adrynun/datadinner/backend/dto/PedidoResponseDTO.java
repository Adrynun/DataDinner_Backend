package com.adrynun.datadinner.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO usado para enviar información de un pedido al frontend.
 */
public class PedidoResponseDTO {

    private Integer id;

    private LocalDateTime fechaHora;

    private String estado;

    private Integer mesaId;

    private Integer usuarioId;

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    private String usuarioNombre;

    private Integer numeroMesa;

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    private List<PedidoProductoDTO> productos;

    public PedidoResponseDTO() {}

    public PedidoResponseDTO(Integer id, LocalDateTime fechaHora, String estado, Integer mesaId, Integer usuarioId, List<PedidoProductoDTO> productos) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.mesaId = mesaId;
        this.usuarioId = usuarioId;
        this.productos = productos;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

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
