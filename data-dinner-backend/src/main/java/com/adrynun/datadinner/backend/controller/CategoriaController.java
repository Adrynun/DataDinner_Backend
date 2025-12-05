package com.adrynun.datadinner.backend.controller;

import com.adrynun.datadinner.backend.dto.CategoriaDTO;
import com.adrynun.datadinner.backend.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de categorías.
 */
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /**
     * Obtener todas las categorías.
     */
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAllCategorias() {
        List<CategoriaDTO> categorias = categoriaService.getAllCategorias();
        return ResponseEntity.ok(categorias);
    }

    /**
     * Obtener una categoría por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable int id) {
        CategoriaDTO categoria = categoriaService.getCategoriaById(id);
        return ResponseEntity.ok(categoria);
    }

    /**
     * Crear una nueva categoría.
     */
    @PostMapping
    public ResponseEntity<CategoriaDTO> createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO saved = categoriaService.saveCategoria(categoriaDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * Actualizar una categoría existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable int id,
            @RequestBody CategoriaDTO categoriaDTO) {
        categoriaDTO.setId(id);
        CategoriaDTO updated = categoriaService.saveCategoria(categoriaDTO);
        return ResponseEntity.ok(updated);
    }

    /**
     * Eliminar una categoría por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable int id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
