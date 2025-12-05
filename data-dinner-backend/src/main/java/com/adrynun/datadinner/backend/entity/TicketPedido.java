package com.adrynun.datadinner.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entidad que representa un ticket de un pedido.
 * Un ticket refleja la cuenta generada por un pedido,
 * con su total y estado de pago.
 */
@Entity
@Table(name = "ticket_pedido")
public class TicketPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Pedido al que pertenece este ticket.
     */
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    /**
     * Total del ticket.
     */
    @Column(nullable = false)
    private BigDecimal total;

    /**
     * Estado del ticket: PENDIENTE o COBRADO.
     */
    @Column(nullable = false)
    private String estado;

    // Constructores
    public TicketPedido() {}

    public TicketPedido(Pedido pedido, BigDecimal total, String estado) {
        this.pedido = pedido;
        this.total = total;
        this.estado = estado;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
}
