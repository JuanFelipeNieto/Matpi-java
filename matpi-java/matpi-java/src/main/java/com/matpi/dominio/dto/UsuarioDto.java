package com.matpi.dominio.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
public class UsuarioDto {
    private String id;
    private String telefono;
    private String contrasena; // Campo de BD
    private String password; // Campo alternativo para formularios
    private String correoElectronico;
    private String rol;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    private String nombreCompleto;
    private Boolean estado;
    private String direccion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaIngreso;
    private String experienciaLaboral;
}
