package com.adrynun.datadinner.backend.entity;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Representa la relación entre un Pedido y un Producto.
 * 
 * Contiene la cantidad de cada producto en un pedido específico.
 * Permite manejar pedidos con múltiples productos y cantidades variables.
 */
@Entity
@Table(name = "pedido_productos")
public class PedidoProducto {

    /** Identificador único de la relación Pedido-Producto */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Pedido al que pertenece este producto */
    @NotNull(message = "El pedido es obligatorio")
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    /** Producto incluido en el pedido */
    @NotNull(message = "El producto es obligatorio")
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    /** Cantidad de este producto en el pedido */
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    @Column(nullable = false)
    private int cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser positivo")
    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    /** Constructor vacío requerido por JPA */
    public PedidoProducto() {
    }

    /** Constructor completo (usado por el Service Layer) */
    public PedidoProducto(Pedido pedido, Producto producto, int cantidad, BigDecimal precioUnitario) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // -------------------- Getters y Setters --------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    // -------------------- equals() y hashCode() --------------------

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PedidoProducto that = (PedidoProducto) o;
        // La identidad se basa en el ID (si ya está persistido)
        return id != 0 && id == that.id;
    }

    @Override
    public int hashCode() {
        // Usa el ID solo si no es 0. Si es 0, usa un valor constante para no romper las
        // colecciones.
        return id != 0 ? Objects.hash(id) : Objects.hash(pedido, producto);
    }
}
