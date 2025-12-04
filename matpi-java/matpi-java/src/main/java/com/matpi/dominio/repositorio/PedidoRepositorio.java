package com.matpi.dominio.repositorio;

import com.matpi.dominio.dto.PedidoDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PedidoRepositorio {
    List<PedidoDto> getAll();

    Optional<PedidoDto> getPedido(Long id);

    PedidoDto save(PedidoDto pedidoDto);

    void delete(Long id);

    // Métodos para reportes
    List<PedidoDto> getPedidosPorEstado(String estado);

    List<PedidoDto> getPedidosPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    List<PedidoDto> getPedidosPorFechaYEstado(LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado);

    Long contarPedidosPorEstado(String estado);

    java.math.BigDecimal calcularTotalVentas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
