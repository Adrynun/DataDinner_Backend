package com.adrynun.datadinner.backend.service;

import com.adrynun.datadinner.backend.dto.MesaDTO;
import com.adrynun.datadinner.backend.entity.Mesa;

import java.util.List;

/**
 * Interfaz que define los métodos de negocio para las mesas.
 * Separa la lógica de negocio del acceso a datos y permite manejar DTOs.
 */
public interface MesaService {

    /**
     * Devuelve todas las mesas.
     * @return Lista de MesaDTO
     */
    List<MesaDTO> getAllMesas();

    /**
     * Devuelve una mesa por su ID.
     * @param id ID de la mesa
     * @return MesaDTO con los datos de la mesa
     */
    MesaDTO getMesaById(int id);

    /**
     * Crea o actualiza una mesa.
     * @param mesaDTO DTO con los datos de la mesa
     * @return MesaDTO guardada
     */
    MesaDTO saveMesa(MesaDTO mesaDTO);

    /**
     * Elimina una mesa por su ID.
     * @param id ID de la mesa a eliminar
     */
    void deleteMesa(int id);

    Mesa getMesaEntityById(Integer mesaId);
}
