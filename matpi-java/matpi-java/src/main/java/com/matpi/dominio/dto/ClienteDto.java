package com.matpi.dominio.dto;

import lombok.Data;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class ClienteDto {
    private BigInteger id;
    private String nombreCompleto;
    private String telefono;
    private LocalDateTime ultimaVisita;
    private Integer totalConsumo;
    private LocalDateTime fechaRegistro;
    private String idEmpleado; // ID del empleado que lo registró
}
