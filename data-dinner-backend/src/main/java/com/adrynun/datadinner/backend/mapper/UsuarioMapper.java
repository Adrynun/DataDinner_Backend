package com.adrynun.datadinner.backend.mapper;

import com.adrynun.datadinner.backend.dto.UsuarioDTO;
import com.adrynun.datadinner.backend.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper para convertir entre Usuario y UsuarioDTO.
 */
@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO toDTO(Usuario usuario);

    Usuario toEntity(UsuarioDTO dto);
}
