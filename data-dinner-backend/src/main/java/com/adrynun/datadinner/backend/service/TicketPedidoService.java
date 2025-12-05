package com.adrynun.datadinner.backend.service;

import com.adrynun.datadinner.backend.dto.TicketPedidoDTO;

import java.util.List;

/**
 * Interfaz que define los métodos de negocio para TicketPedido.
 * Maneja la lógica de negocio y devuelve DTOs en lugar de entidades directamente.
 */
public interface TicketPedidoService {

    /**
     * Guarda un ticket de pedido.
     * @param ticketPedidoDTO DTO del ticket
     * @return TicketPedidoDTO guardado
     */
    TicketPedidoDTO save(TicketPedidoDTO ticketPedidoDTO);

    /**
     * Obtiene todos los tickets pendientes (estado PENDIENTE).
     * @return Lista de TicketPedidoDTO
     */
    List<TicketPedidoDTO> getTicketsPendientes();

    /**
     * Marca un ticket como cobrado (estado COBRADO).
     * @param id ID del ticket a actualizar
     * @return TicketPedidoDTO actualizado
     */
    TicketPedidoDTO marcarCobrado(int id);
}
