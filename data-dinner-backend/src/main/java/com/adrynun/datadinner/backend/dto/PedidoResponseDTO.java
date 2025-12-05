package com.adrynun.datadinner.backend.dto;

import java.time.LocalDateTime;
import java.math.BigDecimal; // Importación necesaria
import java.util.List;

/**
 * DTO usado para enviar información completa de un pedido al frontend.
 * Ahora incluye el campo 'total' y usa PedidoProductoResponseDTO.
 */
public class PedidoResponseDTO {

    private Integer id;

    private LocalDateTime fechaHora;

    private String estado;

    private BigDecimal total; // CRÍTICO: Añadido para mostrar el total del pedido

    private Integer mesaId;

    private Integer usuarioId;

    private String usuarioNombre;

    private Integer numeroMesa;

    // Se cambia a la versión de Response
    private List<PedidoProductoResponseDTO> productos;

    public PedidoResponseDTO() {
    }

    public PedidoResponseDTO(Integer id, LocalDateTime fechaHora, String estado, BigDecimal total, Integer mesaId,
            Integer usuarioId, String usuarioNombre, Integer numeroMesa, List<PedidoProductoResponseDTO> productos) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.total = total;
        this.mesaId = mesaId;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.numeroMesa = numeroMesa;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public List<PedidoProductoResponseDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<PedidoProductoResponseDTO> productos) {
        this.productos = productos;
    }
}