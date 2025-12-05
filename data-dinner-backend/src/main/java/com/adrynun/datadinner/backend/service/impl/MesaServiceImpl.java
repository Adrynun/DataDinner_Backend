package com.adrynun.datadinner.backend.service.impl;

import com.adrynun.datadinner.backend.dto.MesaDTO;
import com.adrynun.datadinner.backend.entity.Mesa;
import com.adrynun.datadinner.backend.exception.MesaNotFoundException;
import com.adrynun.datadinner.backend.mapper.MesaMapper;
import com.adrynun.datadinner.backend.repository.MesaRepository;
import com.adrynun.datadinner.backend.service.MesaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz MesaService.
 * Gestiona las mesas usando repositorio y mapper para convertir entre entidad y
 * DTO.
 */
@Service
public class MesaServiceImpl implements MesaService {

    private final MesaRepository mesaRepository;
    private final MesaMapper mesaMapper;

    public MesaServiceImpl(MesaRepository mesaRepository, MesaMapper mesaMapper) {
        this.mesaRepository = mesaRepository;
        this.mesaMapper = mesaMapper;
    }

    @Override
    public List<MesaDTO> getAllMesas() {
        return mesaRepository.findAll().stream()
                .map(mesaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MesaDTO getMesaById(int id) {
        Mesa mesa = getMesaEntityById(id);
        return mesaMapper.toDTO(mesa);
    }

    @Override
    public MesaDTO saveMesa(MesaDTO mesaDTO) {
        Mesa mesa = mesaMapper.toEntity(mesaDTO);
        Mesa saved = mesaRepository.save(mesa);
        return mesaMapper.toDTO(saved);
    }

    @Override
    public void deleteMesa(int id) {
        Mesa mesa = getMesaEntityById(id);
        mesaRepository.delete(mesa);
    }

    @Override
    public Mesa getMesaEntityById(Integer mesaId) {
        return mesaRepository.findById(mesaId)
                .orElseThrow(() -> new MesaNotFoundException("Mesa con id " + mesaId + " no encontrada"));
    }
}