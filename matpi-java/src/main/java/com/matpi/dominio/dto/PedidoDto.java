package com.matpi.dominio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class PedidoDto {
    private Long id;
    private LocalDateTime fecha;
    private String estado; // Pendiente, En Preparación, Entregado, Pagado, Cancelado
    private BigDecimal valor;
    private String mesa;
    private Integer numeroPersonas;
    private Long idEmpleado;
    private Long idCliente;
    private Long idReserva;
    private List<PedidoProductoDto> productos;

    // Campos adicionales para vistas
    private String nombreCliente;
    private String nombreEmpleado;
}
