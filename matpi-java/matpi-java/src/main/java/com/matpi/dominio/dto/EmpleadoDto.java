package com.matpi.dominio.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
public class EmpleadoDto {
    // Campos de Usuario
    private String id;
    private String telefono;
    private String password; // Solo para creación
    private String correoElectronico;
    private String nombreCompleto;
    private String direccion;
    private String experienciaLaboral;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaIngreso;
    private Boolean estado;
    private String rol;

    // Campos específicos de Empleado
    private String idUsr; // Relacionado con Usuario
    private String eps;
    private String tipoContrato;
    private String contactoEmergenciaNombre;
    private String contactoEmergenciaParentesco;
    private String contactoEmergenciaNumero;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaTerminacionContrato;

    // Relación con Usuario (para lectura)
    private UsuarioDto usuario;
}
