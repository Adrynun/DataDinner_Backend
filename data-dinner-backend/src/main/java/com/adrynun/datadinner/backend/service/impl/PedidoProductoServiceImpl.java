package com.adrynun.datadinner.backend.service.impl;

import com.adrynun.datadinner.backend.dto.PedidoProductoDTO;
import com.adrynun.datadinner.backend.entity.PedidoProducto;
import com.adrynun.datadinner.backend.exception.PedidoProductoNotFoundException;
import com.adrynun.datadinner.backend.mapper.PedidoProductoMapper;
import com.adrynun.datadinner.backend.repository.PedidoProductoRepository;
import com.adrynun.datadinner.backend.service.PedidoProductoService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz PedidoProductoService.
 * Gestiona la lógica de negocio de PedidoProducto usando repositorio y mapper.
 */
@Service
public class PedidoProductoServiceImpl implements PedidoProductoService {

    private final PedidoProductoRepository repository;
    private final PedidoProductoMapper mapper;

    public PedidoProductoServiceImpl(PedidoProductoRepository repository, PedidoProductoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<PedidoProductoDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoProductoDTO getById(int id) {
        PedidoProducto pp = repository.findById(id)
                .orElseThrow(() -> new PedidoProductoNotFoundException(id));
        return mapper.toDTO(pp);
    }

    @Override
    public List<PedidoProductoDTO> getByPedidoId(int pedidoId) {
        return repository.findByPedidoId(pedidoId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoProductoDTO create(PedidoProductoDTO dto) {
        PedidoProducto pp = mapper.toEntity(dto);
        PedidoProducto saved = repository.save(pp);
        return mapper.toDTO(saved);
    }

    @Override
    public PedidoProductoDTO update(int id, PedidoProductoDTO dto) {
        PedidoProducto existing = repository.findById(id)
                .orElseThrow(() -> new PedidoProductoNotFoundException(id));

        existing.setPedido(mapper.toEntity(dto).getPedido());
        existing.setProducto(mapper.toEntity(dto).getProducto());
        existing.setCantidad(dto.getCantidad());

        PedidoProducto updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    public void delete(int id) {
        PedidoProducto existing = repository.findById(id)
                .orElseThrow(() -> new PedidoProductoNotFoundException(id));
        repository.delete(existing);
    }
}
