package com.matpi.dominio.dto;

import lombok.Data;

@Data
public class PedidoProductoDto {
    private Long productoId;
    private String nombreProducto;
    private Integer cantidad;
    private java.math.BigDecimal precioUnitario;
}
