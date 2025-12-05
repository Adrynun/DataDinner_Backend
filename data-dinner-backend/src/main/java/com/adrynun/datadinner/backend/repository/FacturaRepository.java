package com.adrynun.datadinner.backend.repository;

import com.adrynun.datadinner.backend.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio para la entidad Factura.
 */
@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

    /**
     * Busca facturas por el ID del pedido asociado.
     * 
     * @param pedidoId
     * @return
     */
    List<Factura> findByPedidoId(int pedidoId);

    /**
     * Busca facturas que no están cerradas.
     * 
     * @return Lista de facturas abiertas.
     */
    List<Factura> findByCerradaFalse();

    /**
     * Busca facturas dentro de un rango de fechas.
     * 
     * @param inicio
     * @param fin
     * @return Lista de facturas en el rango de fechas.
     */
    List<Factura> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
}
