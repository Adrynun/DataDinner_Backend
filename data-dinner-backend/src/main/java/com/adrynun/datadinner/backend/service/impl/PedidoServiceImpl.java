package com.adrynun.datadinner.backend.service.impl;

import com.adrynun.datadinner.backend.dto.PedidoRequestDTO;
import com.adrynun.datadinner.backend.dto.PedidoResponseDTO;
import com.adrynun.datadinner.backend.entity.Pedido;
import com.adrynun.datadinner.backend.entity.Pedido.Estado;
import com.adrynun.datadinner.backend.entity.PedidoProducto;
import com.adrynun.datadinner.backend.exception.PedidoNotFoundException;
import com.adrynun.datadinner.backend.mapper.PedidoMapper;
import com.adrynun.datadinner.backend.mapper.PedidoProductoMapper;
import com.adrynun.datadinner.backend.repository.PedidoRepository;
import com.adrynun.datadinner.backend.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private final PedidoProductoMapper pedidoProductoMapper; // Inyección directa

    // CONSTRUCTOR
    public PedidoServiceImpl(
            PedidoRepository pedidoRepository,
            PedidoMapper pedidoMapper,
            PedidoProductoMapper pedidoProductoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoMapper = pedidoMapper;
        this.pedidoProductoMapper = pedidoProductoMapper;
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
        // 1. Mapeo inicial, asignación de fecha y estado
        Pedido pedido = pedidoMapper.toEntity(pedidoRequest);
        pedido.setFechaHora(LocalDateTime.now());
        pedido.setEstado(Estado.PENDIENTE);

        // 2. Asegurar la bidireccionalidad y calcular el total del pedido
        if (pedido.getProductos() != null) {
            BigDecimal totalCalculado = BigDecimal.ZERO;
            for (PedidoProducto pp : pedido.getProductos()) {
                pp.setPedido(pedido);

                // *** LÓGICA DE CÁLCULO DEL TOTAL AGREGADA AQUÍ ***
                // Se asume que el mapper (o un paso previo) ya cargó el precioUnitario
                // en el objeto PedidoProducto antes de llegar a este punto.
                if (pp.getPrecioUnitario() != null) {
                    BigDecimal subtotal = pp.getPrecioUnitario().multiply(BigDecimal.valueOf(pp.getCantidad()));
                    totalCalculado = totalCalculado.add(subtotal);
                }
                // ************************************************
            }
            pedido.setTotal(totalCalculado);
        } else {
            pedido.setTotal(BigDecimal.ZERO);
        }

        // 3. Guardar el Pedido
        Pedido saved = pedidoRepository.save(pedido);
        return pedidoMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional
    public PedidoResponseDTO updatePedido(Integer id, PedidoRequestDTO pedidoRequest) {
        // 1. Obtener la entidad existente
        Pedido existingPedido = getPedidoEntityById(id);

        // 2. Usar MapStruct para fusionar los campos básicos (Mesa, Usuario, estado
        // etc.)
        pedidoMapper.updateEntityFromDto(pedidoRequest, existingPedido);

        // 3. Manejar la colección anidada (PedidoProducto)
        existingPedido.getProductos().clear();

        List<PedidoProducto> newProductos = pedidoRequest.getProductos().stream()
                .map(pedidoProductoMapper::toEntity)
                .collect(Collectors.toList());

        // 4. Repoblar la colección, asegurar bidireccionalidad y **RECALCULAR EL
        // TOTAL**
        BigDecimal totalCalculado = BigDecimal.ZERO;

        for (PedidoProducto pp : newProductos) {
            pp.setPedido(existingPedido);
            existingPedido.getProductos().add(pp);

            // *** LÓGICA DE CÁLCULO DEL TOTAL APLICADA EN UPDATE ***
            if (pp.getPrecioUnitario() != null) {
                BigDecimal subtotal = pp.getPrecioUnitario().multiply(BigDecimal.valueOf(pp.getCantidad()));
                totalCalculado = totalCalculado.add(subtotal);
            }
        }
        existingPedido.setTotal(totalCalculado);

        // 5. Guardar los cambios
        Pedido updated = pedidoRepository.save(existingPedido);

        return pedidoMapper.toResponseDTO(updated);
    }

    @Override
    public void deletePedido(int id) {
        // NOTA: Para que esto funcione, la Entidad Pedido debe tener configurado
        // CascadeType.REMOVE en su relación @OneToMany con Factura (ver corrección 2).
        Pedido pedido = getPedidoEntityById(id);
        pedidoRepository.delete(pedido);
    }

    @Override
    public Pedido getPedidoEntityById(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido con id " + id + " no encontrado"));
    }
}