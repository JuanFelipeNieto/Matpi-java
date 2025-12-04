package com.matpi.persistencia.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
public class ClienteEntity {

    @Id
    @Column(name = "id")
    private BigInteger id; // ID manual, no autoincremental

    @Column(name = "nombre_completo", length = 40, nullable = false)
    private String nombreCompleto;

    @Column(name = "telefono", length = 14)
    private String telefono;

    @Column(name = "ultima_visita")
    private LocalDateTime ultimaVisita;

    @Column(name = "total_consumo", nullable = false)
    private Integer totalConsumo = 0;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_usr_empleado", referencedColumnName = "id_usr")
    private EmpleadoEntity empleado;

    // Nota: El modelo original tenía una relación con Usuario a través de Empleado,
    // pero aquí lo vinculamos directamente con EmpleadoEntity para mayor claridad.
}
