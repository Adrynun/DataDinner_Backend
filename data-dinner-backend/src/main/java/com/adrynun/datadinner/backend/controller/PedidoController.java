package com.adrynun.datadinner.backend.controller;

import com.adrynun.datadinner.backend.dto.PedidoRequestDTO;
import com.adrynun.datadinner.backend.dto.PedidoResponseDTO;
import com.adrynun.datadinner.backend.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar pedidos.
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // 1. Listar todos los pedidos
    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> getAllPedidos() {
        List<PedidoResponseDTO> pedidos = pedidoService.getAllPedidos();
        return ResponseEntity.ok(pedidos);
    }

    // 2. Obtener un pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> getPedidoById(@PathVariable Integer id) {
        PedidoResponseDTO pedido = pedidoService.getPedidoById(id);
        return ResponseEntity.ok(pedido);
    }

    // 3. Crear un nuevo pedido
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> createPedido(@Valid @RequestBody PedidoRequestDTO pedidoRequest) {
        PedidoResponseDTO nuevoPedido = pedidoService.savePedido(pedidoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    // 4. Actualizar un pedido existente.
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> updatePedido(
            @PathVariable Integer id,
            @Valid @RequestBody PedidoRequestDTO pedidoRequest) {
        PedidoResponseDTO actualizado = pedidoService.updatePedido(id, pedidoRequest);

        return ResponseEntity.ok(actualizado);
    }

    // 5. Eliminar un pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Integer id) {
        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }
}