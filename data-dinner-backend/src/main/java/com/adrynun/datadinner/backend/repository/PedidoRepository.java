package com.adrynun.datadinner.backend.repository;

import com.adrynun.datadinner.backend.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Pedido.
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    /**
     * Busca pedidos por el ID de la mesa asociada.
     * 
     * @param mesaId ID de la mesa.
     * @return Lista de pedidos de la mesa.
     */
    List<Pedido> findByMesaId(int mesaId);
}
