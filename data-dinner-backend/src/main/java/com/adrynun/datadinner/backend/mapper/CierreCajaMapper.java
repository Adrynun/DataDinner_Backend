package com.adrynun.datadinner.backend.mapper;

import com.adrynun.datadinner.backend.dto.CierreCajaDTO;
import com.adrynun.datadinner.backend.entity.CierreCaja;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper para convertir entre CierreCaja y CierreCajaDTO.
 */
@Mapper(componentModel = "spring")
public interface CierreCajaMapper {

    CierreCajaMapper INSTANCE = Mappers.getMapper(CierreCajaMapper.class);

    CierreCajaDTO toDTO(CierreCaja cierre);

    CierreCaja toEntity(CierreCajaDTO dto);
}
