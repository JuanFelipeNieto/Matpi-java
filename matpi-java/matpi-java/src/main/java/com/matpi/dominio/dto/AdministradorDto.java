package com.matpi.dominio.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AdministradorDto {
    private Long id;
    private LocalDateTime ultFechaLogin;
    private String ultIpLogin;
    private String formacionEducativa;
    private UsuarioDto usuario;
}
