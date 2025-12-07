package com.matpi.dominio.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ReporteDto {
    // Totales generales
    private Integer totalPedidos;
    private BigDecimal totalVentas;

    // Por estado
    private Integer pedidosPendientes;
    private Integer pedidosEnPreparacion;
    private Integer pedidosEntregados;
    private Integer pedidosPagados;
    private Integer pedidosCancelados;

    // Totales por estado
    private BigDecimal totalPendientes;
    private BigDecimal totalEnPreparacion;
    private BigDecimal totalEntregados;
    private BigDecimal totalPagados;
    private BigDecimal totalCancelados;

    public ReporteDto() {
        this.totalPedidos = 0;
        this.totalVentas = BigDecimal.ZERO;
        this.pedidosPendientes = 0;
        this.pedidosEnPreparacion = 0;
        this.pedidosEntregados = 0;
        this.pedidosPagados = 0;
        this.pedidosCancelados = 0;
        this.totalPendientes = BigDecimal.ZERO;
        this.totalEnPreparacion = BigDecimal.ZERO;
        this.totalEntregados = BigDecimal.ZERO;
        this.totalPagados = BigDecimal.ZERO;
        this.totalCancelados = BigDecimal.ZERO;
    }
}
