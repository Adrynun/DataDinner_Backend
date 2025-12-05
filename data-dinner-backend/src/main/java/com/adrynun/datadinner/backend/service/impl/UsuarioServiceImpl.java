package com.adrynun.datadinner.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.adrynun.datadinner.backend.dto.UsuarioDTO;
import com.adrynun.datadinner.backend.entity.Usuario;
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
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario con id " + id + " no encontrado"));
        return usuarioMapper.toDTO(usuario);
    }

    @Override
    public UsuarioDTO saveUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario saved = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(saved);
    }

    @Override
    public void deleteUsuario(int id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario con id " + id + " no encontrado"));
        usuarioRepository.delete(usuario);
    }

    @Override
    public UsuarioDTO authenticate(String nombreUsuario, String pin) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new AuthException("Usuario no encontrado: " + nombreUsuario));

        if (usuario.getPass().equals(pin)) {
            return usuarioMapper.toDTO(usuario);
        } else {
            throw new AuthException("PIN incorrecto.");
        }
    }

    @Override
    public Usuario getUsuarioEntityById(Integer usuarioId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsuarioEntityById'");
    }
}
