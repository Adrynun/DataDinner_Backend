package com.adrynun.datadinner.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidad que representa un cierre de caja diario.
 * Guarda un resumen de los pedidos y facturación del día.
 */
@Entity
@Table(name = "cierre_caja")
public class CierreCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Fecha del cierre de caja.
     */
    @Column(nullable = false)
    private LocalDate fecha;

    /**
     * Total de pedidos registrados en ese día.
     */
    @Column(name = "total_pedidos", nullable = false)
    private Integer totalPedidos;

    /**
     * Total facturado en ese día.
     */
    @Column(name = "total_facturado", nullable = false)
    private BigDecimal totalFacturado;

    /**
     * Resumen de productos vendidos en formato JSON.
     * Almacenado como String, puede ser parseado a JSON en servicios si se necesita.
     */
    @Column(name = "resumen_productos", columnDefinition = "json")
    private String resumenProductos;

    // Constructores
    public CierreCaja() {}

    public CierreCaja(LocalDate fecha, Integer totalPedidos, BigDecimal totalFacturado, String resumenProductos) {
        this.fecha = fecha;
        this.totalPedidos = totalPedidos;
        this.totalFacturado = totalFacturado;
        this.resumenProductos = resumenProductos;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(Integer totalPedidos) {
        this.totalPedidos = totalPedidos;
    }

    public BigDecimal getTotalFacturado() {
        return totalFacturado;
    }

    public void setTotalFacturado(BigDecimal totalFacturado) {
        this.totalFacturado = totalFacturado;
    }

    public String getResumenProductos() {
        return resumenProductos;
    }

    public void setResumenProductos(String resumenProductos) {
        this.resumenProductos = resumenProductos;
    }
}
