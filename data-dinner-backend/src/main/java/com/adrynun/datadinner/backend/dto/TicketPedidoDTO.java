package com.adrynun.datadinner.backend.dto;

import java.math.BigDecimal;

/**
 * DTO para exponer información de un ticket de pedido al frontend.
 */
public class TicketPedidoDTO {

    private Integer id;
    private BigDecimal total;
    private String estado;
    private Integer pedidoId;

    public TicketPedidoDTO() {}

    public TicketPedidoDTO(Integer id, BigDecimal total, String estado, Integer pedidoId) {
        this.id = id;
        this.total = total;
        this.estado = estado;
        this.pedidoId = pedidoId;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }
}
