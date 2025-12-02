package com.matpi.dominio.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProveedorDto {
    private Long id;
    private String nombreEmpresa;
    private String contactoNombre;
    private String telefono;
    private String direccion;
    private List<MateriaPrimaDto> materiasPrimas;
}
