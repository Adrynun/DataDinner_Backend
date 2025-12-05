package com.adrynun.datadinner.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adrynun.datadinner.backend.dto.UsuarioCreateRequest;
import com.adrynun.datadinner.backend.dto.UsuarioDTO;
import com.adrynun.datadinner.backend.entity.Usuario;
import com.adrynun.datadinner.backend.entity.Usuario.Rol;
import com.adrynun.datadinner.backend.exception.AuthException;
import com.adrynun.datadinner.backend.exception.UsuarioNotFoundException;
import com.adrynun.datadinner.backend.mapper.UsuarioMapper;
import com.adrynun.datadinner.backend.repository.UsuarioRepository;
import com.adrynun.datadinner.backend.service.UsuarioService;

/**
 * Implementación de la interfaz UsuarioService.
 * Gestiona los usuarios usando repositorio y mapper para convertir entre
 * entidad y DTO.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO getUsuarioById(int id) {
        Usuario usuario = getUsuarioEntityById(id);
        return usuarioMapper.toDTO(usuario);
    }

    @Override
    @Transactional
    public UsuarioDTO saveUsuario(UsuarioCreateRequest request) { // Sincronizado con UsuarioService
        // 1. Mapear de Request DTO a Entidad
        Usuario usuario = usuarioMapper.toEntity(request);

        // 2. Asignar el rol (MapStruct podría fallar al mapear String a Enum)
        usuario.setRol(Rol.valueOf(request.getRol().toUpperCase()));

        // NOTA: Aquí debería ir el cifrado de la contraseña (BCrypt) en un entorno
        // real.
        // Por ahora, se guarda tal cual.

        Usuario saved = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(saved);
    }

    @Override
    public void deleteUsuario(int id) {
        Usuario usuario = getUsuarioEntityById(id);
        usuarioRepository.delete(usuario);
    }

    @Override
    public UsuarioDTO authenticate(String nombreUsuario, String pin) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new AuthException("Usuario no encontrado: " + nombreUsuario));

        // NOTA: En un entorno real, la contraseña (PIN) nunca se almacenaría en texto
        // plano.
        // Se usaría BCryptPasswordEncoder o similar.
        if (usuario.getPass().equals(pin)) {
            return usuarioMapper.toDTO(usuario);
        } else {
            throw new AuthException("PIN incorrecto.");
        }
    }

    @Override
    @Transactional
    public UsuarioDTO updateUsuario(Integer id, UsuarioCreateRequest request) { // Sincronizado con UsuarioService
        Usuario existingUsuario = getUsuarioEntityById(id);

        // Actualizar los campos que vienen en el Request DTO
        usuarioMapper.updateEntityFromRequest(request, existingUsuario);

        // Asignar el rol (MapStruct podría fallar al mapear String a Enum)
        existingUsuario.setRol(Rol.valueOf(request.getRol().toUpperCase()));

        // Si el request trae una nueva contraseña, la actualizamos
        if (request.getPass() != null && !request.getPass().isEmpty()) {
            existingUsuario.setPass(request.getPass());
        }

        Usuario updated = usuarioRepository.save(existingUsuario);
        return usuarioMapper.toDTO(updated);
    }

    @Override
    public Usuario getUsuarioEntityById(Integer usuarioId) {
        // Implementación real para ser usada por mappers y otros servicios
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario con id " + usuarioId + " no encontrado"));
    }
}