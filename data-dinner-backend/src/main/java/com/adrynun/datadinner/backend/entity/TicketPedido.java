package com.adrynun.datadinner.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entidad que representa un ticket de un pedido.
 * Utiliza el Enum TicketEstado para el estado.
 */
@Entity
@Table(name = "ticket_pedido")
public class TicketPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Pedido al que pertenece este ticket (LAZY loading) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    /** Total del ticket. */
    @Column(nullable = false)
    private BigDecimal total;

    /**
     * Estado del ticket: PENDIENTE o COBRADO (ahora con Enum).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketEstado estado;

    // Constructores
    public TicketPedido() {
    }

    public TicketPedido(Pedido pedido, BigDecimal total, TicketEstado estado) {
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

    public TicketEstado getEstado() {
        return estado;
    }

    public void setEstado(TicketEstado estado) {
        this.estado = estado;
    }

    // -------------------- equals() y hashCode() --------------------

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TicketPedido that = (TicketPedido) o;
        return id != 0 && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != 0 ? Objects.hash(id) : Objects.hash(pedido, total);
    }
}