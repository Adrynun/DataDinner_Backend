package com.adrynun.datadinner.backend.repository;

import com.adrynun.datadinner.backend.entity.PedidoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad PedidoProducto.
 */
@Repository
public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, Integer> {
    /**
     * Busca los productos asociados a un pedido específico.
     * 
     * @param pedidoId ID del pedido.
     * @return Lista de productos del pedido.
     */
    List<PedidoProducto> findByPedidoId(int pedidoId);
}
