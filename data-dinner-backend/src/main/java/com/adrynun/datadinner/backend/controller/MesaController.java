package com.adrynun.datadinner.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.adrynun.datadinner.backend.dto.MesaDTO;
import com.adrynun.datadinner.backend.service.MesaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mesas")
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    // Listar todas las mesas
    @GetMapping
    public List<MesaDTO> getAllMesas() {
        return mesaService.getAllMesas();
    }

    // Obtener una mesa por ID
    @GetMapping("/{id}")
    public MesaDTO getMesaById(@PathVariable int id) {
        return mesaService.getMesaById(id);
    }

    // Crear una nueva mesa
    @PostMapping
    public MesaDTO createMesa(@Valid @RequestBody MesaDTO mesaDTO) {
        return mesaService.saveMesa(mesaDTO);
    }

    // Actualizar una mesa existente
    @PutMapping("/{id}")
    public MesaDTO updateMesa(@PathVariable int id, @Valid @RequestBody MesaDTO mesaDTO) {
        MesaDTO existing = mesaService.getMesaById(id);
        existing.setNumero(mesaDTO.getNumero());
        existing.setEstado(mesaDTO.getEstado());
        existing.setCapacidad(mesaDTO.getCapacidad());
        existing.setX(mesaDTO.getX());
        existing.setY(mesaDTO.getY());
        return mesaService.saveMesa(existing);
    }

    // Eliminar una mesa
    @DeleteMapping("/{id}")
    public void deleteMesa(@PathVariable int id) {
        mesaService.deleteMesa(id);
    }
}
