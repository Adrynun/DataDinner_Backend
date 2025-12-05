package com.adrynun.datadinner.backend.mapper;

import com.adrynun.datadinner.backend.dto.ActualizarPedidoRequestDTO;
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
 * Mapper para convertir entre Pedido, PedidoRequestDTO,
 * ActualizarPedidoRequestDTO y PedidoResponseDTO.
 * Usa PedidoProductoMapper (la versión que maneja Request/Response) para la
 * colección.
 */
@Mapper(componentModel = "spring", uses = { PedidoProductoMapper.class })
public abstract class PedidoMapper {

    @Autowired
    protected MesaService mesaService;

    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    // Inyección del PedidoProductoMapper actualizado (que mapea Request/Response)
    protected PedidoProductoMapper pedidoProductoMapper;

    // --- Mapeo a Entidad (Creación) ---

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaHora", ignore = true)
    @Mapping(target = "total", ignore = true) // El total se calcula en el servicio
    @Mapping(target = "mesa", source = "mesaId")
    @Mapping(target = "usuario", source = "usuarioId")
    // MapStruct mapeará List<PedidoProductoRequestDTO> a List<PedidoProducto>
    // usando PedidoProductoMapper
    @Mapping(target = "productos", source = "productos")
    public abstract Pedido toEntity(PedidoRequestDTO dto);

    // --- Mapeo de Actualización de Pedido (Metadata: Mesa/Usuario) ---

    /**
     * Método de fusión para actualizar una entidad Pedido existente con un DTO de
     * Request.
     * Este método se usaría si se desea cambiar la Mesa o el Usuario asociado.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaHora", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "mesa", source = "mesaId")
    @Mapping(target = "usuario", source = "usuarioId")
    @Mapping(target = "productos", ignore = true) // La colección se gestiona manualmente en el servicio
    public abstract void updateEntityFromDto(PedidoRequestDTO dto, @MappingTarget Pedido entity);

    // --- Mapeo a DTO de Respuesta ---

    /**
     * Convierte la entidad Pedido al DTO de respuesta.
     * Mapea los IDs y obtiene el nombre de la mesa y usuario.
     */
    @Mapping(target = "mesaId", source = "mesa.id")
    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "numeroMesa", source = "mesa.numero")
    @Mapping(target = "usuarioNombre", source = "usuario.nombreUsuario")
    // MapStruct mapeará List<PedidoProducto> a List<PedidoProductoResponseDTO>
    @Mapping(target = "productos", source = "productos")
    public abstract PedidoResponseDTO toResponseDTO(Pedido entity);

    // --- Métodos de Resolución de Relaciones (Many-to-One) ---

    protected Mesa resolveMesa(Integer mesaId) {
        if (mesaId == null)
            return null;
        // Asume que este método existe y lanza NotFoundException si no lo encuentra
        return mesaService.getMesaEntityById(mesaId);
    }

    protected Usuario resolveUsuario(Integer usuarioId) {
        if (usuarioId == null)
            return null;
        // Asume que este método existe y lanza NotFoundException si no lo encuentra
        return usuarioService.getUsuarioEntityById(usuarioId);
    }
}