package com.adrynun.datadinner.backend.service.impl;

import com.adrynun.datadinner.backend.dto.FacturaDTO;
import com.adrynun.datadinner.backend.entity.Pedido;
import com.adrynun.datadinner.backend.entity.Factura;
import com.adrynun.datadinner.backend.exception.FacturaNotFoundException;
import com.adrynun.datadinner.backend.mapper.FacturaMapper;
import com.adrynun.datadinner.backend.repository.FacturaRepository;
import com.adrynun.datadinner.backend.service.FacturaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de FacturaService.
 * Gestiona la lógica de facturación usando repositorio y mapper.
 */
@Service
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;
    private final FacturaMapper facturaMapper;

    public FacturaServiceImpl(FacturaRepository facturaRepository, FacturaMapper facturaMapper) {
        this.facturaRepository = facturaRepository;
        this.facturaMapper = facturaMapper;
    }

    @Override
    public FacturaDTO generarFactura(Pedido pedido) {
        BigDecimal total = pedido.getProductos().stream()
                .map(pp -> pp.getProducto().getPrecio().multiply(BigDecimal.valueOf(pp.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Factura factura = new Factura(pedido, total);
        Factura saved = facturaRepository.save(factura);
        return facturaMapper.toDTO(saved);
    }

    @Override
    public List<FacturaDTO> getFacturasPendientes() {
        return facturaRepository.findByCerradaFalse().stream()
                .map(facturaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FacturaDTO> getFacturasPorRango(LocalDateTime inicio, LocalDateTime fin) {
        return facturaRepository.findByFechaBetween(inicio, fin).stream()
                .map(facturaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void cerrarFactura(int id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new FacturaNotFoundException("Factura con id " + id + " no encontrada"));
        factura.setCerrada(true);
        facturaRepository.save(factura);
    }
}
