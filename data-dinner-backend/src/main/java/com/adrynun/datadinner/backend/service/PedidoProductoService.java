package com.adrynun.datadinner.backend.service;

import com.adrynun.datadinner.backend.dto.PedidoProductoDTO;

import java.util.List;

/**
 * Interfaz que define los métodos de negocio para PedidoProducto.
 * Permite desacoplar la entidad de la capa de negocio y usar DTOs.
 */
public interface PedidoProductoService {

    /**
     * Devuelve todos los PedidoProducto.
     * @return lista de DTOs
     */
    List<PedidoProductoDTO> getAll();

    /**
     * Devuelve un PedidoProducto por ID.
     * @param id ID del PedidoProducto
     * @return DTO
     */
    PedidoProductoDTO getById(int id);

    /**
     * Devuelve los PedidoProducto asociados a un pedido.
     * @param pedidoId ID del pedido
     * @return lista de DTOs
     */
    List<PedidoProductoDTO> getByPedidoId(int pedidoId);

    /**
     * Crea un nuevo PedidoProducto.
     * @param dto DTO a crear
     * @return DTO creado
     */
    PedidoProductoDTO create(PedidoProductoDTO dto);

    /**
     * Actualiza un PedidoProducto existente.
     * @param id ID del PedidoProducto
     * @param dto DTO con nuevos datos
     * @return DTO actualizado
     */
    PedidoProductoDTO update(int id, PedidoProductoDTO dto);

    /**
     * Elimina un PedidoProducto por ID.
     * @param id ID del PedidoProducto
     */
    void delete(int id);
}
