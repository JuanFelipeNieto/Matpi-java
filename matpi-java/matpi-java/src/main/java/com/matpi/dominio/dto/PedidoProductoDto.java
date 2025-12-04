package com.matpi.dominio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoProductoDto {
    private Long id;
    private Long pedidoId;
    private Long productoId;
    private Integer cantidad;

    // Campos adicionales para vistas
    private String nombreProducto;
    private java.math.BigDecimal precioUnitario;
}
