package com.adrynun.datadinner.backend.controller;

import com.adrynun.datadinner.backend.dto.CierreCajaDTO;
import com.adrynun.datadinner.backend.service.CierreCajaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Controlador REST para la gestión del cierre de caja.
 */
@RestController
@RequestMapping("/cierre")
public class CierreCajaController {

    private final CierreCajaService cierreCajaService;

    public CierreCajaController(CierreCajaService cierreCajaService) {
        this.cierreCajaService = cierreCajaService;
    }

    /**
     * Crear un nuevo cierre de caja.
     */
    @PostMapping
    public ResponseEntity<CierreCajaDTO> crearCierre(@RequestBody CierreCajaDTO cierreDTO) {
        CierreCajaDTO saved = cierreCajaService.guardarCierre(cierreDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * Obtener un cierre de caja por fecha (formato "yyyy-MM-dd").
     */
    @GetMapping("/{fecha}")
    public ResponseEntity<CierreCajaDTO> getCierre(@PathVariable String fecha) {
        LocalDate localDate = LocalDate.parse(fecha);
        CierreCajaDTO cierre = cierreCajaService.getCierrePorFecha(localDate);
        return ResponseEntity.ok(cierre);
    }
}
