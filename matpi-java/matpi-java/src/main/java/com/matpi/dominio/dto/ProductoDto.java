package com.matpi.dominio.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductoDto {
    private Long id;
    private String nombreProducto;
    private String descripcion;
    private Integer cantidad;
    private BigDecimal valor;
    private String categoria;
    private String imagen;
    private List<MateriaPrimaDto> materiasPrimas;
}
