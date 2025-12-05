package com.adrynun.datadinner.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importación necesaria

import com.adrynun.datadinner.backend.dto.ProductoDTO;
import com.adrynun.datadinner.backend.entity.Categoria;
import com.adrynun.datadinner.backend.entity.Producto;
import com.adrynun.datadinner.backend.exception.CategoriaNotFoundException; // Asumo esta excepción
import com.adrynun.datadinner.backend.exception.ProductoNotFoundException;
import com.adrynun.datadinner.backend.mapper.ProductoMapper;
import com.adrynun.datadinner.backend.repository.CategoriaRepository; // Asumo este repositorio
import com.adrynun.datadinner.backend.repository.ProductoRepository;
import com.adrynun.datadinner.backend.service.ProductoService;

/**
 * Implementación de la interfaz ProductoService.
 * Gestiona los productos usando repositorio y mapper para convertir entre
 * entidad y DTO.
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final CategoriaRepository categoriaRepository; // Inyección del repositorio de Categoría

    public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper,
            CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<ProductoDTO> getAllProductos() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO getProductoById(int id) {
        Producto producto = getProductoEntityById(id);
        return productoMapper.toDTO(producto);
    }

    @Override
    @Transactional // Usamos Transactional para asegurar las operaciones de BBDD
    public ProductoDTO saveProducto(ProductoDTO productoDTO) {
        // 1. Cargar la entidad Categoria
        Categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaId())
                .orElseThrow(() -> new CategoriaNotFoundException(
                        "Categoría con id " + productoDTO.getCategoriaId() + " no encontrada"));

        // 2. Mapear DTO a Producto (esto mapea nombre, precio, etc.)
        Producto producto = productoMapper.toEntity(productoDTO);

        // 3. Asignar la entidad Categoria cargada
        producto.setCategoria(categoria);

        // 4. Guardar y devolver
        Producto saved = productoRepository.save(producto);
        return productoMapper.toDTO(saved);
    }

    @Override
    public void deleteProducto(int id) {
        Producto producto = getProductoEntityById(id);
        productoRepository.delete(producto);
    }

    @Override
    public List<ProductoDTO> getProductosByCategoriaId(int categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId)
                .stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional // Usamos Transactional para asegurar las operaciones de BBDD
    public ProductoDTO updateProducto(Integer id, ProductoDTO productoDTO) {
        // 1. Obtenemos la entidad existente
        Producto existingProducto = getProductoEntityById(id);

        // 2. Si el ID de categoría ha cambiado, cargamos la nueva entidad Categoria
        if (!productoDTO.getCategoriaId().equals(existingProducto.getCategoria().getId())) {
            Categoria newCategoria = categoriaRepository.findById(productoDTO.getCategoriaId())
                    .orElseThrow(() -> new CategoriaNotFoundException(
                            "Categoría con id " + productoDTO.getCategoriaId() + " no encontrada"));
            existingProducto.setCategoria(newCategoria);
        }

        // 3. Actualizamos el resto de campos (nombre, precio, etc.)
        productoMapper.updateEntityFromDto(productoDTO, existingProducto);

        // 4. Guardamos y devolvemos el DTO actualizado
        Producto updated = productoRepository.save(existingProducto);
        return productoMapper.toDTO(updated);
    }

    @Override
    public Producto getProductoEntityById(Integer id) {
        // Implementación para ser usada por mappers y otros servicios
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto con id " + id + " no encontrado"));
    }
}