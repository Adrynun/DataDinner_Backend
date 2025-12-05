package com.adrynun.datadinner.backend.service;

import com.adrynun.datadinner.backend.dto.CierreCajaDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Interfaz que define los métodos de negocio para CierreCaja.
 */
public interface CierreCajaService {

    /**
     * Guarda un cierre de caja.
     * @param cierreCajaDTO DTO con los datos del cierre
     * @return DTO guardado
     */
    CierreCajaDTO guardarCierre(CierreCajaDTO cierreCajaDTO);

    /**
     * Obtiene un cierre de caja por fecha.
     * @param fecha Fecha del cierre
     * @return DTO del cierre
     */
    CierreCajaDTO getCierrePorFecha(LocalDate fecha);

    /**
     * Devuelve todos los cierres de caja.
     * @return Lista de DTOs
     */
    List<CierreCajaDTO> getAllCierres();
}
