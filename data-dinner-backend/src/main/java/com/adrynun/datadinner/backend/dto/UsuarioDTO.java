package com.adrynun.datadinner.backend.dto;

/**
 * DTO para exponer información de un usuario sin incluir la contraseña.
 */
public class UsuarioDTO {

    private Integer id;
    private String nombreUsuario;
    private String rol;

    public UsuarioDTO() {}

    public UsuarioDTO(Integer id, String nombreUsuario, String rol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.rol = rol;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
