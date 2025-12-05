package com.adrynun.datadinner.backend.mapper;

import com.adrynun.datadinner.backend.dto.CategoriaDTO;
import com.adrynun.datadinner.backend.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper para convertir entre Categoria y CategoriaDTO.
 */
@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    CategoriaDTO toDTO(Categoria categoria);

    Categoria toEntity(CategoriaDTO categoriaDTO);
}
