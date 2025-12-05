package com.adrynun.datadinner.backend.mapper;

import com.adrynun.datadinner.backend.dto.ProductoDTO;
import com.adrynun.datadinner.backend.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * Mapper para convertir entre Producto y ProductoDTO.
 * * Se añaden los mappings explícitos para la categoría:
 * - 'categoria.id' -> 'categoriaId'
 * - 'categoria.nombre' -> 'categoriaNombre'
 */
@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "categoria.nombre", target = "categoriaNombre")
    ProductoDTO toDTO(Producto producto);

    // No se necesita el mapeo inverso para toEntity si el servicio maneja la carga
    // de la entidad Categoria

    Producto toEntity(ProductoDTO productoDTO);

    /**
     * Actualiza una entidad Producto existente con los datos de un ProductoDTO.
     * Los mappings se aplican implícitamente o pueden re-declararse si son
     * necesarios.
     * * @param productoDTO DTO con los datos a aplicar.
     * 
     * @param producto    Entidad a actualizar (destino).
     */
    void updateEntityFromDto(ProductoDTO productoDTO, @MappingTarget Producto producto);
}