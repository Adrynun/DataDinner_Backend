package com.adrynun.datadinner.backend.mapper;

import com.adrynun.datadinner.backend.dto.FacturaDTO;
import com.adrynun.datadinner.backend.entity.Factura;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper para convertir entre Factura y FacturaDTO.
 */
@Mapper(componentModel = "spring")
public interface FacturaMapper {

    FacturaMapper INSTANCE = Mappers.getMapper(FacturaMapper.class);

    FacturaDTO toDTO(Factura factura);

    Factura toEntity(FacturaDTO dto);
}
