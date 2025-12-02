package com.matpi.dominio.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReservaDto {
    private Long id;
    private LocalDateTime fechaReserva;
    private Integer numeroPersonas;
    private String mesa;
    private String estado;
    private String observaciones;
    private String idCliente; // ID del cliente si está registrado
    private String nombreCliente; // Nombre si no está registrado
}
