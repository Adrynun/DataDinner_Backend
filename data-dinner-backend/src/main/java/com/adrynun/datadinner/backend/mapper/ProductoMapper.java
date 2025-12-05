package com.adrynun.datadinner.backend.mapper;

import com.adrynun.datadinner.backend.dto.ProductoDTO;
import com.adrynun.datadinner.backend.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper para convertir entre Producto y ProductoDTO.
 */
@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

    ProductoDTO toDTO(Producto producto);

    Producto toEntity(ProductoDTO productoDTO);
}
