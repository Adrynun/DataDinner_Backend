package com.adrynun.datadinner.backend.controller;

import com.adrynun.datadinner.backend.dto.ProductoDTO;
import com.adrynun.datadinner.backend.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // ---------------------------------------
    // Obtener todos los productos
    // ---------------------------------------
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAllProductos() {
        return ResponseEntity.ok(productoService.getAllProductos());
    }

    // ---------------------------------------
    // Obtener un producto por ID
    // ---------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Integer id) {
        ProductoDTO producto = productoService.getProductoById(id);
        return ResponseEntity.ok(producto);
    }

    // ---------------------------------------
    // Crear un producto
    // ---------------------------------------
    @PostMapping
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO nuevo = productoService.saveProducto(productoDTO);
        return ResponseEntity.ok(nuevo);
    }

    // ---------------------------------------
    // Actualizar un producto
    // ---------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> updateProducto(
            @PathVariable Integer id,
            @RequestBody ProductoDTO productoDTO) {

        ProductoDTO actualizado = productoService.updateProducto(id, productoDTO);
        return ResponseEntity.ok(actualizado);
    }

    // ---------------------------------------
    // Eliminar un producto
    // ---------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
