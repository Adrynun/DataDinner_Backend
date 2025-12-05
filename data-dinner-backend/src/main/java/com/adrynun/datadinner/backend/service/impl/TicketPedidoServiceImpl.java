package com.adrynun.datadinner.backend.service.impl;

import com.adrynun.datadinner.backend.dto.TicketPedidoDTO;
import com.adrynun.datadinner.backend.entity.TicketPedido;
import com.adrynun.datadinner.backend.entity.TicketEstado;
import com.adrynun.datadinner.backend.exception.TicketPedidoNotFoundException;
import com.adrynun.datadinner.backend.mapper.TicketPedidoMapper;
import com.adrynun.datadinner.backend.repository.TicketPedidoRepository;
import com.adrynun.datadinner.backend.service.TicketPedidoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz TicketPedidoService.
 * Gestiona los tickets de pedidos usando repositorio y mapper para convertir entre entidad y DTO.
 */
@Service
public class TicketPedidoServiceImpl implements TicketPedidoService {

    private final TicketPedidoRepository repository;
    private final TicketPedidoMapper mapper;

    public TicketPedidoServiceImpl(TicketPedidoRepository repository, TicketPedidoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TicketPedidoDTO save(TicketPedidoDTO ticketPedidoDTO) {
        TicketPedido ticket = mapper.toEntity(ticketPedidoDTO);
        TicketPedido saved = repository.save(ticket);
        return mapper.toDTO(saved);
    }

    @Override
    public List<TicketPedidoDTO> getTicketsPendientes() {
        // Buscamos por el valor string del Enum (asumiendo que el repositorio espera un String)
        return repository.findByEstado(TicketEstado.PENDIENTE.toString()).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TicketPedidoDTO marcarCobrado(int id) {
        TicketPedido ticket = repository.findById(id)
                .orElseThrow(() -> new TicketPedidoNotFoundException("Ticket no encontrado: " + id));
        
        // CORREGIDO: Usar el Enum TicketEstado
        ticket.setEstado(TicketEstado.COBRADO);
        
        TicketPedido updated = repository.save(ticket);
        return mapper.toDTO(updated);
    }
}