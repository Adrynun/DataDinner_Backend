package com.adrynun.datadinner.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para la creación y actualización de un Usuario.
 * Incluye la contraseña (pass) para poder realizar las validaciones de
 * persistencia.
 */
public class UsuarioCreateRequest {

    private Integer id;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres")
    private String nombreUsuario;

    @NotBlank(message = "La contraseña (PIN) es obligatoria")
    @Size(min = 2, max = 8, message = "La contraseña (PIN) debe tener entre 4 y 8 caracteres")
    private String pass;

    @NotBlank(message = "El rol es obligatorio")
    private String rol;

    // Constructores (vacío y con todos los campos)
    public UsuarioCreateRequest() {
    }

    public UsuarioCreateRequest(Integer id, String nombreUsuario, String pass, String rol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.pass = pass;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}