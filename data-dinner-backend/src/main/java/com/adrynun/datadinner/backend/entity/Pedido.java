package com.adrynun.datadinner.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa un pedido realizado en el restaurante.
 * CRÍTICO: Se añade el campo 'total' para evitar recálculos en las consultas.
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

    /** Total calculado del pedido (para rendimiento) */
    @NotNull(message = "El total del pedido es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El total no puede ser negativo")
    @Column(nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    /** Usuario que registró el pedido (LAZY loading) */
    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY) // Siempre LAZY para ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    /** Mesa asociada al pedido (LAZY loading) */
    @NotNull(message = "La mesa es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;

    /**
     * Lista de productos incluidos en el pedido.
     * La cascada es necesaria aquí: si eliminas el pedido, se eliminan sus líneas.
     */
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoProducto> productos = new ArrayList<>();

    /**
     * CORRECCIÓN: Relación OneToMany con las facturas generadas a partir de este
     * pedido.
     * Añadimos CascadeType.REMOVE para eliminar las facturas si se elimina el
     * Pedido,
     * resolviendo el error de clave foránea.
     */
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Factura> facturas = new ArrayList<>();

    /** Estados posibles de un pedido */
    public enum Estado {
        EN_PREPARACION, SERVIDO, CERRADO, PENDIENTE
    }

    /** Constructor vacío requerido por JPA */
    public Pedido() {
    }

    /** Constructor con usuario y mesa */
    public Pedido(Usuario usuario, Mesa mesa) {
        this.usuario = usuario;
        this.mesa = mesa;
        this.estado = Estado.EN_PREPARACION;
        this.total = BigDecimal.ZERO;
    }

    /**
     * Calcula el total del pedido sumando el subtotal de cada PedidoProducto.
     * Este método se usa en la capa de servicio, no debe ser usado para
     * persistencia
     * ya que el campo 'total' ya existe.
     * 
     * @return El total calculado (BigDecimal).
     */
    public BigDecimal calcularTotal() {
        if (this.productos == null || this.productos.isEmpty()) {
            return BigDecimal.ZERO;
        }

        // Suma el precio total de cada PedidoProducto
        BigDecimal totalCalculado = this.productos.stream()
                // Map: Calcula el subtotal de cada línea: precio * cantidad
                .map(pp -> pp.getPrecioUnitario().multiply(new BigDecimal(pp.getCantidad())))
                // Reduce: Suma todos los subtotales
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalCalculado;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    // -------------------- equals() y hashCode() --------------------

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pedido pedido = (Pedido) o;
        // La identidad se basa en el ID
        return id != 0 && id == pedido.id;
    }

    @Override
    public int hashCode() {
        return id != 0 ? Objects.hash(id) : Objects.hash(fechaHora, usuario, mesa);
    }
}