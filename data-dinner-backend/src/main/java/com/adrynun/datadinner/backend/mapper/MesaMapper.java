package com.adrynun.datadinner.backend.mapper;

import com.adrynun.datadinner.backend.dto.MesaDTO;
import com.adrynun.datadinner.backend.entity.Mesa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper para convertir entre Mesa y MesaDTO.
 */
@Mapper(componentModel = "spring")
public interface MesaMapper {

    MesaMapper INSTANCE = Mappers.getMapper(MesaMapper.class);

    MesaDTO toDTO(Mesa mesa);

    Mesa toEntity(MesaDTO dto);
}
