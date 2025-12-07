package com.matpi.dominio.servicios;

import com.matpi.dominio.dto.PedidoDto;
import com.matpi.dominio.repositorio.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    public List<PedidoDto> getAll() {
        return pedidoRepositorio.getAll();
    }

    public Optional<PedidoDto> getPedido(Long id) {
        return pedidoRepositorio.getPedido(id);
    }

    public PedidoDto save(PedidoDto pedidoDto) {
        return pedidoRepositorio.save(pedidoDto);
    }

    public boolean delete(Long id) {
        return getPedido(id).map(pedido -> {
            pedidoRepositorio.delete(id);
            return true;
        }).orElse(false);
    }

    // Métodos para reportes
    public List<PedidoDto> getPedidosPorEstado(String estado) {
        return pedidoRepositorio.getPedidosPorEstado(estado);
    }

    public List<PedidoDto> getPedidosPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return pedidoRepositorio.getPedidosPorFecha(fechaInicio, fechaFin);
    }

    public List<PedidoDto> getPedidosPorFechaYEstado(LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado) {
        return pedidoRepositorio.getPedidosPorFechaYEstado(fechaInicio, fechaFin, estado);
    }

    public Long contarPedidosPorEstado(String estado) {
        return pedidoRepositorio.contarPedidosPorEstado(estado);
    }

    public BigDecimal calcularTotalVentas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return pedidoRepositorio.calcularTotalVentas(fechaInicio, fechaFin);
    }
}
