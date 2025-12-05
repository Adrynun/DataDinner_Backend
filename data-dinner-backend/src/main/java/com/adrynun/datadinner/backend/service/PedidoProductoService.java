package com.adrynun.datadinner.backend.service;

import com.adrynun.datadinner.backend.dto.PedidoProductoRequestDTO;
import com.adrynun.datadinner.backend.dto.PedidoProductoResponseDTO;

import java.util.List;

/**
 * Interfaz que define los métodos de negocio para PedidoProducto (líneas de
 * pedido).
 * Permite desacoplar la entidad de la capa de negocio y usar DTOs de
 * Request/Response.
 */
public interface PedidoProductoService {

    /**
     * Devuelve todos los PedidoProducto (solo para fines de gestión/auditoría).
     * 
     * @return lista de DTOs de respuesta
     */
    List<PedidoProductoResponseDTO> getAll();

    /**
     * Devuelve un PedidoProducto por ID.
     * 
     * @param id ID del PedidoProducto
     * @return DTO de respuesta
     */
    PedidoProductoResponseDTO getById(int id);

    /**
     * Devuelve los PedidoProducto asociados a un pedido.
     * 
     * @param pedidoId ID del pedido
     * @return lista de DTOs de respuesta
     */
    List<PedidoProductoResponseDTO> getByPedidoId(int pedidoId);

    /**
     * Crea un nuevo PedidoProducto.
     * Este método probablemente se usará internamente dentro de PedidoService,
     * pero se mantiene aquí para coherencia CRUD.
     * 
     * @param dto DTO de solicitud (solo productoId y cantidad)
     * @return DTO de respuesta creado
     */
    PedidoProductoResponseDTO create(PedidoProductoRequestDTO dto);

    /**
     * Actualiza un PedidoProducto existente.
     * 
     * @param id  ID del PedidoProducto
     * @param dto DTO de solicitud con nuevos datos (solo productoId y cantidad)
     * @return DTO de respuesta actualizado
     */
    PedidoProductoResponseDTO update(int id, PedidoProductoRequestDTO dto);

    /**
     * Elimina un PedidoProducto por ID.
     * 
     * @param id ID del PedidoProducto
     */
    void delete(int id);
}