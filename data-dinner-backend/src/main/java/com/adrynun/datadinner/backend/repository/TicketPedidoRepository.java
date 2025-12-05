package com.adrynun.datadinner.backend.repository;

import com.adrynun.datadinner.backend.entity.TicketPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad TicketPedido.
 */
@Repository
public interface TicketPedidoRepository extends JpaRepository<TicketPedido, Integer> {

    /**
     * Encuentra tickets por el ID del pedido asociado.
     * @param pedidoId ID del pedido
     * @return Lista de TicketPedido asociados al pedido
     */
    List<TicketPedido> findByPedidoId(int pedidoId);

    /** 
     * Encuentra tickets por su estado.
     * @param estado Estado del ticket (e.g., "PENDIENTE", "COBRADO")
     * @return Lista de TicketPedido con el estado especificado
     */
    List<TicketPedido> findByEstado(String estado);
}
