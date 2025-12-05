package com.adrynun.datadinner.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrynun.datadinner.backend.dto.LoginRequestDTO;
import com.adrynun.datadinner.backend.dto.LoginResponseDTO;
import com.adrynun.datadinner.backend.dto.UsuarioDTO;
import com.adrynun.datadinner.backend.service.UsuarioService;

/**
 * Controlador para autenticación de usuarios.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para login de usuario.
     *
     * @param loginRequest DTO con nombre de usuario y contraseña
     * @return LoginResponseDTO con información del usuario autenticado
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            // Ahora usamos UsuarioDTO
            UsuarioDTO usuarioDTO = usuarioService.authenticate(
                    loginRequest.getNombreUsuario(),
                    loginRequest.getPass());

            LoginResponseDTO response = new LoginResponseDTO(
                    usuarioDTO.getId(),
                    usuarioDTO.getNombreUsuario(),
                    usuarioDTO.getRol());

            return ResponseEntity.ok(response);

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
    }
}
