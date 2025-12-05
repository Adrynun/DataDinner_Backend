package com.adrynun.datadinner.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * DTO para la entidad Mesa.
 * Permite enviar información de mesas al frontend.
 */
public class MesaDTO {

    private Integer id;

    @Positive(message = "El número de mesa debe ser mayor que 0")
    private int numero;

    @NotNull(message = "El estado de la mesa es obligatorio")
    private String estado; // LIBRE, OCUPADA, RESERVADA, CUENTA

    @Min(value = 1, message = "La capacidad debe ser al menos 1")
    private int capacidad;

    @Min(value = 0, message = "La coordenada X no puede ser negativa")
    private int x;

    @Min(value = 0, message = "La coordenada Y no puede ser negativa")
    private int y;

    public MesaDTO() {}

    public MesaDTO(Integer id, int numero, String estado, int capacidad, int x, int y) {
        this.id = id;
        this.numero = numero;
        this.estado = estado;
        this.capacidad = capacidad;
        this.x = x;
        this.y = y;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
