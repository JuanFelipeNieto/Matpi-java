package com.matpi.persistencia.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reserva")
@Data
@NoArgsConstructor
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDateTime fechaReserva;

    @Column(name = "numero_personas", nullable = false)
    private Integer numeroPersonas;

    @Column(name = "mesa")
    private String mesa;

    @Column(name = "estado", nullable = false)
    private String estado; // Pendiente, Confirmada, Cancelada, Completada

    @Column(name = "observaciones")
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "reserva")
    private List<PedidoEntity> pedidos;
}
