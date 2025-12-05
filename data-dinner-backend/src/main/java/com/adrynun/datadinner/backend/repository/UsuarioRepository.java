package com.adrynun.datadinner.backend.repository;

import com.adrynun.datadinner.backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad Usuario.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    /**
     * Busca un usuario por su nombre de usuario.
     * 
     * @param nombreUsuario Nombre de usuario.
     * @return Usuario opcional.
     */
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
