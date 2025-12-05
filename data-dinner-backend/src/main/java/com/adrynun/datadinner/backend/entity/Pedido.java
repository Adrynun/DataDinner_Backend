package com.adrynun.datadinner.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un pedido realizado en el restaurante.
 * 
 * Cada pedido está asociado a una mesa y a un usuario (camarero),
 * y contiene una lista de productos a través de la entidad PedidoProducto.
 */
@Entity
@Table(name = "pedidos")
public class Pedido {

    /** Identificador único del pedido */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Fecha y hora en que se realizó el pedido */
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora = LocalDateTime.now();

    /** Estado actual del pedido */
    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    /** Usuario que registró el pedido */
    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    /** Mesa asociada al pedido */
    @NotNull(message = "La mesa es obligatoria")
    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;

    /** Lista de productos incluidos en el pedido */
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoProducto> productos = new ArrayList<>();

    /** Estados posibles de un pedido */
    public enum Estado {
        EN_PREPARACION, SERVIDO, CERRADO, PENDIENTE
    }

    /** Constructor vacío requerido por JPA */
    public Pedido() {}

    /** Constructor con usuario y mesa */
    public Pedido(Usuario usuario, Mesa mesa) {
        this.usuario = usuario;
        this.mesa = mesa;
        this.estado = Estado.EN_PREPARACION;
    }

    // -------------------- Getters y Setters --------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<PedidoProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<PedidoProducto> productos) {
        this.productos = productos;
    }

    public void setEstado(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEstado'");
    }
}
