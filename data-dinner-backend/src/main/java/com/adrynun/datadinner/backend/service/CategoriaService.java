package com.adrynun.datadinner.backend.service;

import com.adrynun.datadinner.backend.dto.CategoriaDTO;

import java.util.List;

/**
 * Interfaz que define los métodos de negocio para las categorías.
 * Separa la lógica de negocio del acceso a datos y permite manejar DTOs.
 */
public interface CategoriaService {

    /**
     * Devuelve una categoría por su ID.
     * @param id ID de la categoría
     * @return CategoriaDTO con los datos de la categoría
     */
    CategoriaDTO getCategoriaById(int id);

    /**
     * Devuelve todas las categorías.
     * @return Lista de CategoriaDTO
     */
    List<CategoriaDTO> getAllCategorias();

    /**
     * Crea o actualiza una categoría.
     * @param categoriaDTO DTO con los datos de la categoría
     * @return CategoriaDTO guardada
     */
    CategoriaDTO saveCategoria(CategoriaDTO categoriaDTO);

    /**
     * Elimina una categoría por su ID.
     * @param id ID de la categoría a eliminar
     */
    void deleteCategoria(int id);
}
