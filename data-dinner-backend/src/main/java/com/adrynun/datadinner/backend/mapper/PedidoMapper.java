package com.adrynun.datadinner.backend.mapper;

import com.adrynun.datadinner.backend.dto.PedidoRequestDTO;
import com.adrynun.datadinner.backend.dto.PedidoResponseDTO;
import com.adrynun.datadinner.backend.entity.Mesa;
import com.adrynun.datadinner.backend.entity.Pedido;
import com.adrynun.datadinner.backend.entity.Usuario;
import com.adrynun.datadinner.backend.service.MesaService;
import com.adrynun.datadinner.backend.service.UsuarioService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mapper para convertir entre Pedido, PedidoRequestDTO y PedidoResponseDTO.
 * Esta clase es abstracta para permitir la inyección de servicios (Service Injection)
 * necesaria para resolver las relaciones.
 */
@Mapper(componentModel = "spring", uses = { PedidoProductoMapper.class })
public abstract class PedidoMapper {

    @Autowired
    protected MesaService mesaService;

    @Autowired
    protected UsuarioService usuarioService;

    // Inyección para usar en el servicio, solucionando el error PedidoMapperJava(67108964)
    @Autowired
    protected PedidoProductoMapper pedidoProductoMapper; 

    // --- Métodos de Mapeo a Entidad ---

    /**
     * Convierte el DTO de solicitud a la entidad Pedido.
     * Ignora el ID y delega la resolución de IDs de Mesa y Usuario a los métodos auxiliares.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true) // El estado se asigna en el servicio (PENDIENTE)
    @Mapping(target = "fechaHora", ignore = true) // La fecha/hora se asigna en el servicio
    @Mapping(target = "mesa", source = "mesaId")
    @Mapping(target = "usuario", source = "usuarioId")
    @Mapping(target = "productos", source = "productos")
    public abstract Pedido toEntity(PedidoRequestDTO dto);

    /**
     * Método de fusión para actualizar una entidad existente con un DTO.
     * Ignora el ID, estado y fechaHora para que no sean sobrescritos por el DTO de entrada.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaHora", ignore = true)
    @Mapping(target = "mesa", source = "mesaId")
    @Mapping(target = "usuario", source = "usuarioId")
    @Mapping(target = "productos", ignore = true) // La colección se gestiona manualmente en el servicio
    public abstract void updateEntityFromDto(PedidoRequestDTO dto, @MappingTarget Pedido entity);

    // --- Métodos de Mapeo a DTO de Respuesta ---

    /**
     * Convierte la entidad Pedido al DTO de respuesta.
     * Mapea los objetos Mesa y Usuario a sus IDs.
     */
    @Mapping(target = "mesaId", source = "mesa.id")
    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "productos", source = "productos")
    public abstract PedidoResponseDTO toResponseDTO(Pedido entity);


    // --- Métodos de Resolución de Relaciones (Many-to-One) ---

    protected Mesa resolveMesa(Integer mesaId) {
        if (mesaId == null) return null;
        return mesaService.getMesaEntityById(mesaId);
    }

    protected Usuario resolveUsuario(Integer usuarioId) {
        if (usuarioId == null) return null;
        return usuarioService.getUsuarioEntityById(usuarioId);
    }

    // --- Método para exponer el Mapper de PedidoProducto (Fix para el error 2) ---

    /**
     * Expone el PedidoProductoMapper inyectado para ser usado en la capa de servicio
     * al manejar la actualización manual de la colección.
     */
    public PedidoProductoMapper getPedidoProductoMapper() {
        return pedidoProductoMapper;
    }
}