package com.matpi.dominio.dto;

import lombok.Data;

@Data
public class MateriaPrimaDto {
    private Long id;
    private String nombre;
    private Double cantidadDisponible;
    private String unidadMedida;
}
