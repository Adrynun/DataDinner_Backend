package com.adrynun.datadinner.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO para exponer información de un cierre de caja al frontend.
 */
public class CierreCajaDTO {

    private Integer id;
    private LocalDate fecha;
    private int totalPedidos;
    private BigDecimal totalFacturado;
    private String resumenProductos; // JSON con detalle de productos

    public CierreCajaDTO() {}

    public CierreCajaDTO(Integer id, LocalDate fecha, int totalPedidos, BigDecimal totalFacturado, String resumenProductos) {
        this.id = id;
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

    public int getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(int totalPedidos) {
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
