package com.adrynun.datadinner.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad que representa una factura generada a partir de un pedido.
 * Puede estar cerrada o pendiente de cierre para el control de caja diario.
 */
@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Fecha y hora en la que se genera la factura.
     * Se establece por defecto al crear la entidad.
     */
    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    /**
     * Pedido asociado a esta factura.
     * Un pedido puede tener una factura asociada.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    /**
     * Total facturado en esta factura.
     */
    @Column(nullable = false)
    private BigDecimal total;

    /**
     * Indica si la factura está cerrada o pendiente.
     * Usado para control de cierre de caja diario.
     */
    @Column(nullable = false)
    private boolean cerrada = false;

    // Constructores
    public Factura() {}

    public Factura(Pedido pedido, BigDecimal total) {
        this.pedido = pedido;
        this.total = total;
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

    public boolean isCerrada() {
        return cerrada;
    }

    public void setCerrada(boolean cerrada) {
        this.cerrada = cerrada;
    }
}
