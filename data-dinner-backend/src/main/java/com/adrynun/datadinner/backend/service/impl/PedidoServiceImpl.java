package com.adrynun.datadinner.backend.service.impl;

import com.adrynun.datadinner.backend.dto.PedidoRequestDTO;
import com.adrynun.datadinner.backend.dto.PedidoResponseDTO;
import com.adrynun.datadinner.backend.entity.Pedido;
import com.adrynun.datadinner.backend.entity.PedidoProducto;
import com.adrynun.datadinner.backend.exception.PedidoNotFoundException;
import com.adrynun.datadinner.backend.mapper.PedidoMapper;
import com.adrynun.datadinner.backend.repository.PedidoRepository;
import com.adrynun.datadinner.backend.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz PedidoService.
 * Gestiona los pedidos usando repositorio y mapper para convertir entre entidad
 * y DTOs.
 */
@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public List<PedidoResponseDTO> getAllPedidos() {
        return pedidoRepository.findAll().stream()
                .map(pedidoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoResponseDTO getPedidoById(int id) {
        Pedido pedido = getPedidoEntityById(id);
        return pedidoMapper.toResponseDTO(pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO savePedido(PedidoRequestDTO pedidoRequest) {
        // En la creación, asignamos la fecha y el estado iniciales
        Pedido pedido = pedidoMapper.toEntity(pedidoRequest);
        pedido.setFechaHora(LocalDateTime.now());
        pedido.setEstado("PENDIENTE");

        // Aseguramos la bidireccionalidad en la lista de PedidoProducto antes de
        // guardar
        if (pedido.getProductos() != null) {
            for (PedidoProducto pp : pedido.getProductos()) {
                pp.setPedido(pedido);
            }
        }

        Pedido saved = pedidoRepository.save(pedido);
        return pedidoMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional
    public PedidoResponseDTO updatePedido(Integer id, PedidoRequestDTO pedidoRequest) {
        // 1. Obtener la entidad existente
        Pedido existingPedido = getPedidoEntityById(id);

        // 2. Usar MapStruct para fusionar los campos básicos (Mesa, Usuario, etc.)
        // La lógica para convertir IDs a Entidades está en el PedidoMapper
        pedidoMapper.updateEntityFromDto(pedidoRequest, existingPedido);

        // 3. Manejar la colección anidada (PedidoProducto)
        // **CRÍTICO: Limpiar y repoblar la lista para activar orphanRemoval=true**

        // Limpiamos la colección existente (esto eliminará los registros huérfanos de
        // la DB)
        existingPedido.getProductos().clear();

        // Mapeamos los DTOs de PedidoProducto a entidades PedidoProducto
        List<PedidoProducto> newProductos = pedidoRequest.getProductos().stream()
                .map(pedidoMapper.getPedidoProductoMapper()::toEntity) // Usamos el mapper de PP
                .collect(Collectors.toList());

        // Repoblamos la colección con los nuevos ítems
        for (PedidoProducto pp : newProductos) {
            // Aseguramos la bidireccionalidad para JPA
            pp.setPedido(existingPedido);
            existingPedido.getProductos().add(pp);
        }

        // 4. Guardar los cambios (JPA persistirá automáticamente la colección)
        Pedido updated = pedidoRepository.save(existingPedido);

        return pedidoMapper.toResponseDTO(updated);
    }

    @Override
    public void deletePedido(int id) {
        Pedido pedido = getPedidoEntityById(id);
        pedidoRepository.delete(pedido);
    }

    @Override
    public Pedido getPedidoEntityById(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido con id " + id + " no encontrado"));
    }
}