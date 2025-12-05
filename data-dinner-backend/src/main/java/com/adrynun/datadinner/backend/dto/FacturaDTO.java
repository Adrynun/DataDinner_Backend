package com.adrynun.datadinner.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO para exponer información de una factura al frontend.
 */
public class FacturaDTO {

    private Integer id;
    private LocalDateTime fecha;
    private BigDecimal total;
    private boolean cerrada;
    private Integer pedidoId;

    public FacturaDTO() {}

    public FacturaDTO(Integer id, LocalDateTime fecha, BigDecimal total, boolean cerrada, Integer pedidoId) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.cerrada = cerrada;
        this.pedidoId = pedidoId;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean isCerrada() {
        return cerrada;
    }

    public void setCerrada(boolean cerrada) {
        this.cerrada = cerrada;
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }
}
