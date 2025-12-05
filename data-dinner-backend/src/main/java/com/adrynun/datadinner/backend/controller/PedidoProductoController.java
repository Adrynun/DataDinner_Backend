package com.adrynun.datadinner.backend.controller;

import com.adrynun.datadinner.backend.dto.PedidoProductoDTO;
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

    // 1. Listar todos los PedidoProducto
    @GetMapping
    public ResponseEntity<List<PedidoProductoDTO>> getAll() {
        return ResponseEntity.ok(pedidoProductoService.getAll());
    }

    // 2. Obtener uno por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoProductoDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(pedidoProductoService.getById(id));
    }

    // 3. Crear un PedidoProducto
    @PostMapping
    public ResponseEntity<PedidoProductoDTO> create(
            @Valid @RequestBody PedidoProductoDTO dto) {

        PedidoProductoDTO created = pedidoProductoService.create(dto);
        return ResponseEntity.ok(created);
    }

    // 4. Actualizar un PedidoProducto existente
    @PutMapping("/{id}")
    public ResponseEntity<PedidoProductoDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody PedidoProductoDTO dto) {

        PedidoProductoDTO updated = pedidoProductoService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // 5. Eliminar un PedidoProducto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pedidoProductoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
