package com.matpi.persistencia.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "materia_prima")
@Data
@NoArgsConstructor
public class MateriaPrimaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "cantidad_disponible", nullable = false)
    private Double cantidadDisponible;

    @Column(name = "unidad_medida", nullable = false, length = 20)
    private String unidadMedida;

    @ManyToMany(mappedBy = "materiasPrimas")
    private List<ProductoEntity> productos;

    @ManyToMany(mappedBy = "materiasPrimas")
    private List<ProveedorEntity> proveedores;
}
