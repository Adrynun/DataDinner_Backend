package com.adrynun.datadinner.backend.mapper;

import com.adrynun.datadinner.backend.dto.PedidoProductoDTO;
import com.adrynun.datadinner.backend.entity.PedidoProducto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper para convertir entre PedidoProducto y PedidoProductoDTO.
 */
@Mapper(componentModel = "spring")
public interface PedidoProductoMapper {

    PedidoProductoMapper INSTANCE = Mappers.getMapper(PedidoProductoMapper.class);

    PedidoProductoDTO toDTO(PedidoProducto pedidoProducto);

    PedidoProducto toEntity(PedidoProductoDTO dto);
}
