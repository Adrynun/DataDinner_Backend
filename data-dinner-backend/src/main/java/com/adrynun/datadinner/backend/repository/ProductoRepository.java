package com.adrynun.datadinner.backend.repository;

import com.adrynun.datadinner.backend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Producto.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    /**
     * Busca productos por el ID de la categoría asociada.
     * 
     * @param categoriaId ID de la categoría.
     * @return Lista de productos de la categoría.
     */
    List<Producto> findByCategoriaId(int categoriaId);
}
