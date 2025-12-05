package com.adrynun.datadinner.backend.service;

import com.adrynun.datadinner.backend.dto.UsuarioDTO;
import com.adrynun.datadinner.backend.entity.Usuario;

import java.util.List;

/**
 * Interfaz que define los métodos de negocio para los usuarios.
 * Separa la lógica de negocio del acceso a datos y permite manejar DTOs.
 */
public interface UsuarioService {

    /**
     * Devuelve todos los usuarios.
     * @return Lista de UsuarioDTO
     */
    List<UsuarioDTO> getAllUsuarios();

    /**
     * Devuelve un usuario por su ID.
     * @param id ID del usuario
     * @return UsuarioDTO con los datos del usuario
     */
    UsuarioDTO getUsuarioById(int id);

    /**
     * Crea o actualiza un usuario.
     * @param usuarioDTO DTO con los datos del usuario
     * @return UsuarioDTO guardado
     */
    UsuarioDTO saveUsuario(UsuarioDTO usuarioDTO);

    /**
     * Elimina un usuario por su ID.
     * @param id ID del usuario a eliminar
     */
    void deleteUsuario(int id);

    /**
     * Autentica un usuario por nombre de usuario y contraseña.
     * @param nombreUsuario Nombre de usuario
     * @param pin Contraseña
     * @return UsuarioDTO autenticado
     */
    UsuarioDTO authenticate(String nombreUsuario, String pin);

    Usuario getUsuarioEntityById(Integer usuarioId);
}
