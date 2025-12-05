package com.adrynun.datadinner.backend.service.impl;

import com.adrynun.datadinner.backend.dto.CierreCajaDTO;
import com.adrynun.datadinner.backend.entity.CierreCaja;
import com.adrynun.datadinner.backend.exception.CierreCajaNotFoundException;
import com.adrynun.datadinner.backend.mapper.CierreCajaMapper;
import com.adrynun.datadinner.backend.repository.CierreCajaRepository;
import com.adrynun.datadinner.backend.service.CierreCajaService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz CierreCajaService.
 * Gestiona cierres de caja usando repositorio y mapper para convertir entre entidad y DTO.
 */
@Service
public class CierreCajaServiceImpl implements CierreCajaService {

    private final CierreCajaRepository repository;
    private final CierreCajaMapper mapper;

    public CierreCajaServiceImpl(CierreCajaRepository repository, CierreCajaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CierreCajaDTO guardarCierre(CierreCajaDTO cierreCajaDTO) {
        CierreCaja cierre = mapper.toEntity(cierreCajaDTO);
        CierreCaja saved = repository.save(cierre);
        return mapper.toDTO(saved);
    }

    @Override
    public CierreCajaDTO getCierrePorFecha(LocalDate fecha) {
        CierreCaja cierre = repository.findByFecha(fecha)
                .orElseThrow(() -> new CierreCajaNotFoundException("No existe cierre para la fecha: " + fecha));
        return mapper.toDTO(cierre);
    }

    @Override
    public List<CierreCajaDTO> getAllCierres() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
