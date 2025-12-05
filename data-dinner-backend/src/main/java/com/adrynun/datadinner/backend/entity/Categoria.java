package com.adrynun.datadinner.backend.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una categoría de productos en el sistema.
 * 
 * Cada categoría puede contener múltiples productos asociados.
 * Se corresponde con la tabla 'categorias' en la base de datos.
 */
@Entity
@Table(name = "categorias")
public class Categoria {

    /** Identificador único de la categoría */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Nombre de la categoría (único y obligatorio) */
    @Column(nullable = false, unique = true)
    private String nombre;

    /**
     * Lista de productos asociados a esta categoría.
     * La relación es bidireccional.
     */
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos = new ArrayList<>();

    /** Constructor vacío requerido por JPA */
    public Categoria() {}

    /** Constructor con nombre */
    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    // -------------------- Getters y Setters --------------------

    /**
     * Obtiene el ID de la categoría.
     * @return id de la categoría
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID de la categoría.
     * Normalmente lo maneja JPA.
     * @param id ID de la categoría
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la categoría.
     * @return nombre de la categoría
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la categoría.
     * @param nombre nombre de la categoría
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la lista de productos asociados a la categoría.
     * @return lista de productos
     */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
     * Establece la lista de productos asociados.
     * @param productos lista de productos
     */
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
