package com.matpi.dominio.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoDto {
    private Long id;
    private LocalDateTime fecha;
    private String estado;
    private BigDecimal valor;
    private String mesa;
    private Integer numeroPersonas;
    private String idEmpleado;
    private String idCliente; // ID del cliente si aplica
    private Long idReserva;
    private List<PedidoProductoDto> productos;
}
