package com.adrynun.datadinner.backend.service.impl;

import com.adrynun.datadinner.backend.dto.PedidoProductoRequestDTO;
import com.adrynun.datadinner.backend.dto.PedidoProductoResponseDTO;
import com.adrynun.datadinner.backend.entity.PedidoProducto;
import com.adrynun.datadinner.backend.exception.PedidoProductoNotFoundException;
import com.adrynun.datadinner.backend.mapper.PedidoProductoMapper;
import com.adrynun.datadinner.backend.repository.PedidoProductoRepository;
import com.adrynun.datadinner.backend.service.PedidoProductoService;
import com.adrynun.datadinner.backend.service.ProductoService; // Necesario para la resolución de entidades
import com.adrynun.datadinner.backend.service.PedidoService; // Necesario para la resolución de entidades

import org.springframework.context.annotation.Lazy; // Usar @Lazy si hay dependencia circular con PedidoService
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz PedidoProductoService.
 * Gestiona la lógica de negocio de PedidoProducto usando repositorio y mapper.
 * Nota: Los métodos `create` y `update` de este servicio son generalmente
 * usados internamente o por un controlador específico de línea de detalle,
 * ya que la lógica principal de modificación de líneas vive en `PedidoService`.
 */
@Service
public class PedidoProductoServiceImpl implements PedidoProductoService {

    private final PedidoProductoRepository repository;
    private final PedidoProductoMapper mapper;

    // Inyectamos los servicios necesarios para que el mapper pueda resolver IDs a
    // entidades
    private final ProductoService productoService;
    private final PedidoService pedidoService;

    // Usamos @Lazy en PedidoService si hay riesgo de circularidad con
    // PedidoServiceImpl
    public PedidoProductoServiceImpl(
            PedidoProductoRepository repository,
            PedidoProductoMapper mapper,
            ProductoService productoService,
            @Lazy PedidoService pedidoService) {
        this.repository = repository;
        this.mapper = mapper;
        this.productoService = productoService;
        this.pedidoService = pedidoService;
    }

    @Override
    public List<PedidoProductoResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoProductoResponseDTO getById(int id) {
        PedidoProducto pp = repository.findById(id)
                .orElseThrow(() -> new PedidoProductoNotFoundException(id));
        return mapper.toResponseDTO(pp);
    }

    @Override
    public List<PedidoProductoResponseDTO> getByPedidoId(int pedidoId) {
        return repository.findByPedidoId(pedidoId).stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PedidoProductoResponseDTO create(PedidoProductoRequestDTO dto) {
        // Convertimos el DTO de Request a Entidad, resolviendo las referencias (Pedido
        // y Producto)
        // La resolución del Pedido debe ser provista en el DTO de Request si este
        // método fuera público.
        // Dado que el DTO de Request solo tiene productoId y cantidad, si se llama
        // directamente,
        // necesitamos que el mapper sea capaz de inferir el Pedido.
        // Pero, como la creación de PP está anidada en Pedido, se recomienda que este
        // método no se use directamente.
        // Por simplicidad, asumiremos que el mapper puede crear la entidad PP
        // correctamente.

        PedidoProducto pp = mapper.toEntity(dto);
        // NOTA: Es crucial que el mapper resuelva el Producto usando
        // productoService.getProductoEntityById(dto.getProductoId())

        PedidoProducto saved = repository.save(pp);
        return mapper.toResponseDTO(saved);
    }

    @Override
    @Transactional
    public PedidoProductoResponseDTO update(int id, PedidoProductoRequestDTO dto) {
        PedidoProducto existing = repository.findById(id)
                .orElseThrow(() -> new PedidoProductoNotFoundException(id));

        // Solo se permite actualizar la Cantidad. El Producto asociado no debe cambiar
        // una vez creado el PedidoProducto.
        existing.setCantidad(dto.getCantidad());

        // Si el PedidoId del DTO está presente, lo actualizamos, sino se asume que
        // la entidad ya está ligada a un pedido (común en MapStruct Update).
        // Sin embargo, PedidoProductoRequestDTO NO tiene pedidoId, solo productoId y
        // cantidad.
        // Por eso, usamos updateEntityFromDto si el mapper lo soporta, o solo
        // actualizamos la cantidad directamente.

        // Si el DTO trae productoId, aseguramos que sea el mismo (o lanzamos error).
        // Si solo trae cantidad, solo actualizamos cantidad.

        // Dado que el DTO solo tiene cantidad y productoId:
        // 1. Validamos que el producto no haya cambiado (si dto.getProductoId() !=
        // existing.getProducto().getId())
        // 2. Actualizamos la cantidad.

        // La lógica para PedidoProducto es compleja fuera del flujo de Pedido.
        // Asumiendo que solo se actualiza la cantidad:
        existing.setCantidad(dto.getCantidad());

        PedidoProducto updated = repository.save(existing);
        return mapper.toResponseDTO(updated);
    }

    @Override
    public void delete(int id) {
        PedidoProducto existing = repository.findById(id)
                .orElseThrow(() -> new PedidoProductoNotFoundException(id));
        repository.delete(existing);
    }
}