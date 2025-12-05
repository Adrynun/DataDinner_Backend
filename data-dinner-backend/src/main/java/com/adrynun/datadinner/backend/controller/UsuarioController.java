package com.adrynun.datadinner.backend.controller;

import com.adrynun.datadinner.backend.dto.UsuarioCreateRequest; // IMPORTACIÓN NECESARIA
import com.adrynun.datadinner.backend.dto.UsuarioDTO;
import com.adrynun.datadinner.backend.service.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // -------------------------------------------------------
    // GET: Obtener todos los usuarios
    // -------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // -------------------------------------------------------
    // GET: Obtener usuario por ID
    // -------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Integer id) {
        UsuarioDTO usuario = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }

    // -------------------------------------------------------
    // POST: Crear usuario
    // -------------------------------------------------------
    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(
            @Valid @RequestBody UsuarioCreateRequest usuarioRequest) { // Usamos el DTO de Request

        // Llamamos a saveUsuario con el DTO de Request que incluye el 'pass'
        UsuarioDTO created = usuarioService.saveUsuario(usuarioRequest);
        return ResponseEntity.ok(created);
    }

    // -------------------------------------------------------
    // PUT: Actualizar usuario
    // -------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(
            @PathVariable Integer id,
            @Valid @RequestBody UsuarioCreateRequest usuarioRequest) { // Usamos el DTO de Request

        // Llamamos al método updateUsuario con el ID y el DTO de Request
        UsuarioDTO updated = usuarioService.updateUsuario(id, usuarioRequest);
        return ResponseEntity.ok(updated);
    }

    // -------------------------------------------------------
    // DELETE: Eliminar usuario
    // -------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}