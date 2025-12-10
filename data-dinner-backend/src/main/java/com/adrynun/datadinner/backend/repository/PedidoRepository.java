package com.adrynun.datadinner.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adrynun.datadinner.backend.entity.Pedido;

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

    /**
     * Busca un Pedido por ID, cargando de forma ansiosa (EAGER)
     * el usuario, la mesa y los productos para evitar LazyInitializationException.
     *
     * @param id ID del pedido.
     * @return El Pedido con las colecciones y entidades relacionadas cargadas.
     */
    @Query("SELECT p FROM Pedido p " +
           "JOIN FETCH p.usuario u " +
           "JOIN FETCH p.mesa m " +
           "LEFT JOIN FETCH p.productos pp " + // LEFT JOIN por si no tiene productos
           "WHERE p.id = :id")
    Optional<Pedido> findByIdWithDetails(@Param("id") Integer id);
}
