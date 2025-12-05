package com.adrynun.datadinner.backend.service;

import com.adrynun.datadinner.backend.dto.ProductoDTO;

import java.util.List;

/**
 * Interfaz que define los métodos de negocio para productos.
 * Separa la lógica de negocio del acceso a datos y permite manejar DTOs.
 */
public interface ProductoService {

    /**
     * Devuelve todos los productos.
     * @return Lista de ProductoDTO
     */
    List<ProductoDTO> getAllProductos();

    /**
     * Devuelve un producto por su ID.
     * @param id ID del producto
     * @return ProductoDTO correspondiente
     */
    ProductoDTO getProductoById(int id);

    /**
     * Crea o actualiza un producto.
     * @param productoDTO DTO del producto a guardar
     * @return ProductoDTO guardado
     */
    ProductoDTO saveProducto(ProductoDTO productoDTO);

    /**
     * Elimina un producto por su ID.
     * @param id ID del producto a eliminar
     */
    void deleteProducto(int id);

    /**
     * Devuelve todos los productos de una categoría específica.
     * @param categoriaId ID de la categoría
     * @return Lista de ProductoDTO
     */
    List<ProductoDTO> getProductosByCategoriaId(int categoriaId);

    ProductoDTO updateProducto(Integer id, ProductoDTO productoDTO);
}
