package com.adrynun.datadinner.backend.service.impl;

import com.adrynun.datadinner.backend.dto.CategoriaDTO;
import com.adrynun.datadinner.backend.entity.Categoria;
import com.adrynun.datadinner.backend.exception.CategoriaNotFoundException;
import com.adrynun.datadinner.backend.mapper.CategoriaMapper;
import com.adrynun.datadinner.backend.repository.CategoriaRepository;
import com.adrynun.datadinner.backend.service.CategoriaService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz CategoriaService.
 * Gestiona las categorías usando repositorio y mapper para convertir entre entidad y DTO.
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public CategoriaDTO getCategoriaById(int id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException("Categoría con id " + id + " no encontrada"));
        return categoriaMapper.toDTO(categoria);
    }

    @Override
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO saveCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Categoria saved = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(saved);
    }

    @Override
    public void deleteCategoria(int id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException("Categoría con id " + id + " no encontrada"));
        categoriaRepository.delete(categoria);
    }
}
