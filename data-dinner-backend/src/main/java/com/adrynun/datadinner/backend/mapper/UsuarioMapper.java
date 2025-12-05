package com.adrynun.datadinner.backend.mapper;

import com.adrynun.datadinner.backend.dto.UsuarioCreateRequest; // Nuevo DTO de entrada
import com.adrynun.datadinner.backend.dto.UsuarioDTO;
import com.adrynun.datadinner.backend.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * Mapper para convertir entre Usuario, UsuarioDTO y UsuarioCreateRequest.
 * Se excluye el campo 'pass' en el mapeo a UsuarioDTO por seguridad.
 */
@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    // Mapeo de Entidad a DTO de Respuesta (Excluye 'pass', mapea Enum a String)
    @Mapping(target = "rol", source = "rol") // CORRECCIÓN: Quitamos .name para mapeo automático Enum -> String
    UsuarioDTO toDTO(Usuario usuario);

    // Mapeo de DTO de Solicitud a Entidad (Mapea String a Enum, incluye 'pass')
    @Mapping(target = "rol", expression = "java(Usuario.Rol.valueOf(request.getRol().toUpperCase()))")
    Usuario toEntity(UsuarioCreateRequest request);

    // El método toEntity(UsuarioDTO dto) de la versión anterior ya no es necesario
    // para save/update.

    /**
     * Actualiza una entidad Usuario existente con los datos de un
     * UsuarioCreateRequest.
     * Ignoramos el rol y la contraseña ya que el servicio los maneja por separado
     * (setRol, setPass).
     * 
     * @param request DTO con los datos a aplicar.
     * @param usuario Entidad a actualizar (destino).
     */
    @Mapping(target = "rol", ignore = true) // El rol se maneja en el servicio
    @Mapping(target = "pass", ignore = true) // La contraseña se maneja en el servicio
    void updateEntityFromRequest(UsuarioCreateRequest request, @MappingTarget Usuario usuario);
}