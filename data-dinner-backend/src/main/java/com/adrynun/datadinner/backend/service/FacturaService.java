package com.adrynun.datadinner.backend.service;

import com.adrynun.datadinner.backend.dto.FacturaDTO;
import com.adrynun.datadinner.backend.entity.Pedido;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz que define los métodos de negocio para facturas.
 * Gestiona la creación, consulta y cierre de facturas.
 */
public interface FacturaService {

    /**
     * Genera una factura a partir de un pedido.
     * @param pedido Pedido asociado a la factura
     * @return FacturaDTO generada
     */
    FacturaDTO generarFactura(Pedido pedido);

    /**
     * Devuelve todas las facturas pendientes (no cerradas).
     * @return Lista de FacturaDTO
     */
    List<FacturaDTO> getFacturasPendientes();

    /**
     * Devuelve facturas en un rango de fechas.
     * @param inicio Fecha/hora inicio
     * @param fin Fecha/hora fin
     * @return Lista de FacturaDTO
     */
    List<FacturaDTO> getFacturasPorRango(LocalDateTime inicio, LocalDateTime fin);

    /**
     * Cierra una factura (marcando como cerrada).
     * @param id ID de la factura a cerrar
     */
    void cerrarFactura(int id);
}
