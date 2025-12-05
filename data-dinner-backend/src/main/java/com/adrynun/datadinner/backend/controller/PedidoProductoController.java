package com.adrynun.datadinner.backend.controller;

import com.adrynun.datadinner.backend.dto.PedidoProductoRequestDTO;
import com.adrynun.datadinner.backend.dto.PedidoProductoResponseDTO;
import com.adrynun.datadinner.backend.service.PedidoProductoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar los productos de un pedido.
 */
@RestController
@RequestMapping("/pedido-productos")
public class PedidoProductoController {

    private final PedidoProductoService pedidoProductoService;

    public PedidoProductoController(PedidoProductoService pedidoProductoService) {
        this.pedidoProductoService = pedidoProductoService;
    }

    // 1. Listar todos los PedidoProducto (Usa ResponseDTO para salida)
    @GetMapping
    public ResponseEntity<List<PedidoProductoResponseDTO>> getAll() {
        // Corrección del error 1: El servicio devuelve List<PedidoProductoResponseDTO>
        return ResponseEntity.ok(pedidoProductoService.getAll());
    }

    // 2. Obtener uno por ID (Usa ResponseDTO para salida)
    @GetMapping("/{id}")
    public ResponseEntity<PedidoProductoResponseDTO> getById(@PathVariable Integer id) {
        // Corrección del error 2: El servicio devuelve PedidoProductoResponseDTO
        return ResponseEntity.ok(pedidoProductoService.getById(id));
    }

    // 3. Crear un PedidoProducto (Usa RequestDTO para entrada y ResponseDTO para
    // salida)
    @PostMapping
    public ResponseEntity<PedidoProductoResponseDTO> create(
            // Corrección del error 3: Usa RequestDTO para la entrada
            @Valid @RequestBody PedidoProductoRequestDTO dto) {

        PedidoProductoResponseDTO created = pedidoProductoService.create(dto);
        return ResponseEntity.ok(created);
    }

    // 4. Actualizar un PedidoProducto existente (Usa RequestDTO para entrada y
    // ResponseDTO para salida)
    @PutMapping("/{id}")
    public ResponseEntity<PedidoProductoResponseDTO> update(
            @PathVariable Integer id,
            // Corrección del error 4: Usa RequestDTO para la entrada
            @Valid @RequestBody PedidoProductoRequestDTO dto) {

        PedidoProductoResponseDTO updated = pedidoProductoService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // 5. Eliminar un PedidoProducto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pedidoProductoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}