package com.adrynun.datadinner.backend.service;

import com.adrynun.datadinner.backend.dto.PedidoRequestDTO;
import com.adrynun.datadinner.backend.dto.PedidoResponseDTO;
import com.adrynun.datadinner.backend.entity.Pedido;

import java.util.List;

/**
 * Interfaz que define los métodos de negocio para los pedidos.
 * Separa la lógica de negocio del acceso a datos y permite manejar DTOs.
 */
public interface PedidoService {

    /**
     * Devuelve todos los pedidos.
     * 
     * @return Lista de PedidoResponseDTO
     */
    List<PedidoResponseDTO> getAllPedidos();

    /**
     * Devuelve un pedido por su ID.
     * 
     * @param id ID del pedido
     * @return PedidoResponseDTO con los datos del pedido
     */
    PedidoResponseDTO getPedidoById(int id);

    /**
     * Crea un nuevo pedido a partir de un DTO de solicitud.
     * 
     * @param pedidoRequest PedidoRequestDTO con los datos del pedido
     * @return PedidoResponseDTO del pedido guardado
     */
    PedidoResponseDTO savePedido(PedidoRequestDTO pedidoRequest);

    /**
     * Actualiza un pedido existente.
     * Es responsable de buscar el Pedido original, aplicar los cambios del DTO
     * (incluyendo la actualización de la lista de productos anidados) y guardarlo.
     * @param id            ID del pedido a actualizar
     * @param pedidoRequest DTO con los datos actualizados
     * @return PedidoResponseDTO del pedido actualizado
     */
    PedidoResponseDTO updatePedido(Integer id, PedidoRequestDTO pedidoRequest);

    /**
     * Elimina un pedido por su ID.
     * 
     * @param id ID del pedido a eliminar
     */
    void deletePedido(int id);

    /**
     * Obtiene la entidad Pedido por su ID.
     * 
     * @param id ID del pedido
     * @return Entidad Pedido
     */
    Pedido getPedidoEntityById(Integer id);
}