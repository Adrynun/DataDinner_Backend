package com.adrynun.datadinner.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa una Mesa en el restaurante.
 * Contiene información sobre su número, estado, capacidad y ubicación.
 * Además, mantiene la relación con los pedidos asociados.
 */
@Entity
@Table(name = "mesas")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Positive(message = "El número de mesa debe ser mayor que 0")
    @Column(nullable = false, unique = true)
    private int numero;

    @NotNull(message = "El estado de la mesa es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Min(value = 1, message = "La capacidad debe ser al menos 1")
    @Column(nullable = false)
    private int capacidad;

    @Min(value = 0, message = "La coordenada X no puede ser negativa")
    private Integer x;

    @Min(value = 0, message = "La coordenada Y no puede ser negativa")
    private Integer y;

    /**
     * Relación OneToMany con los pedidos de la mesa.
     * Se elimina en cascada si la mesa se elimina.
     */
    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    /**
     * Enum que representa el estado actual de la mesa.
     */
    public enum Estado {
        LIBRE,
        OCUPADA,
        RESERVADA,
        CUENTA
    }

    // Constructores
    public Mesa() {}

    public Mesa(int numero, Estado estado, int capacidad, Integer x, Integer y) {
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
