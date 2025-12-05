package com.adrynun.datadinner.backend.repository;

import com.adrynun.datadinner.backend.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad Categoria.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    /**
     * Encuentra una categoria por su nombre.
     *
     * @param nombre El nombre de la categoria.
     * @return Una instancia Optional que contiene la categoria si se encuentra, o vacía si no.
     */
    Optional<Categoria> findByNombre(String nombre);
}
