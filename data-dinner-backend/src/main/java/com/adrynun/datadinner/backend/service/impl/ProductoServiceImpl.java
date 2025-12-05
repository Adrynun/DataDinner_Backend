package com.adrynun.datadinner.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.adrynun.datadinner.backend.dto.ProductoDTO;
import com.adrynun.datadinner.backend.entity.Producto;
import com.adrynun.datadinner.backend.exception.ProductoNotFoundException;
import com.adrynun.datadinner.backend.mapper.ProductoMapper;
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

    public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
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
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto con id " + id + " no encontrado"));
        return productoMapper.toDTO(producto);
    }

    @Override
    public ProductoDTO saveProducto(ProductoDTO productoDTO) {
        Producto producto = productoMapper.toEntity(productoDTO);
        Producto saved = productoRepository.save(producto);
        return productoMapper.toDTO(saved);
    }

    @Override
    public void deleteProducto(int id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto con id " + id + " no encontrado"));
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
    public ProductoDTO updateProducto(Integer id, ProductoDTO productoDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProducto'");
    }

}
