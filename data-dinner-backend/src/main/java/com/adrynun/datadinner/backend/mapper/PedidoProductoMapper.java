package com.adrynun.datadinner.backend.mapper;

import com.adrynun.datadinner.backend.dto.PedidoProductoRequestDTO;
import com.adrynun.datadinner.backend.dto.PedidoProductoResponseDTO;
import com.adrynun.datadinner.backend.entity.PedidoProducto;
import com.adrynun.datadinner.backend.entity.Producto;
import com.adrynun.datadinner.backend.service.ProductoService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Mapper para convertir entre PedidoProducto Entity y sus DTOs de Request y
 * Response.
 * Es una clase abstracta para permitir la inyección de ProductoService.
 */
@Mapper(componentModel = "spring")
public abstract class PedidoProductoMapper {

    @Autowired
    protected ProductoService productoService;

    // --- Mapeo de Request (Entrada) a Entidad (Creación/Actualización) ---

    /**
     * Convierte el DTO de solicitud (solo productoId y cantidad) a la entidad
     * PedidoProducto.
     * CRÍTICO: La entidad se rellena con el precio actual del producto.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pedido", ignore = true) // El Pedido se asigna en la capa de servicio
    @Mapping(target = "producto", source = "productoId") // Resuelve la entidad Producto
    @Mapping(target = "precioUnitario", expression = "java(getPrecioUnitario(dto.getProductoId()))") // Rellena el
                                                                                                     // precio
    public abstract PedidoProducto toEntity(PedidoProductoRequestDTO dto);

    // Método de ayuda para obtener la entidad Producto.
    protected Producto resolveProducto(Integer productoId) {
        if (productoId == null)
            return null;
        return productoService.getProductoEntityById(productoId); // Asume que este método existe en ProductoService
    }

    // Método de ayuda para obtener el precio unitario.
    protected java.math.BigDecimal getPrecioUnitario(Integer productoId) {
        Producto producto = resolveProducto(productoId);
        return producto != null ? producto.getPrecio() : null; // Retorna el precio
    }

    // --- Mapeo de Entidad a Response (Salida) ---

    /**
     * Convierte la entidad PedidoProducto a su DTO de respuesta.
     * Rellena el nombre del producto para el frontend.
     */
    @Mapping(target = "productoId", source = "producto.id")
    @Mapping(target = "pedidoId", source = "pedido.id")
    @Mapping(target = "nombreProducto", source = "producto.nombre") // Añade el nombre del producto
    public abstract PedidoProductoResponseDTO toResponseDTO(PedidoProducto entity);

    // Mapeo de lista
    public abstract List<PedidoProductoResponseDTO> toResponseDTOList(List<PedidoProducto> entities);

}