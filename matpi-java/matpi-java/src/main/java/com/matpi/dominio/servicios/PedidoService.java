package com.matpi.dominio.servicios;

import com.matpi.dominio.dto.PedidoDto;
import com.matpi.dominio.repositorio.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public PedidoDto update(PedidoDto pedidoDto) {
        return pedidoRepositorio.save(pedidoDto);
    }

    public boolean delete(Long id) {
        return getPedido(id).map(pedido -> {
            pedidoRepositorio.delete(id);
            return true;
        }).orElse(false);
    }

    public List<PedidoDto> filtrarPedidos(String estado, String fechaDesde, String fechaHasta) {
        List<PedidoDto> pedidos = getAll();

        // Filtrar por estado
        if (estado != null && !estado.isEmpty()) {
            pedidos = pedidos.stream()
                    .filter(p -> p.getEstado().equals(estado))
                    .collect(Collectors.toList());
        }

        // Filtrar por fecha desde
        if (fechaDesde != null && !fechaDesde.isEmpty()) {
            LocalDateTime desde = LocalDate.parse(fechaDesde).atStartOfDay();
            pedidos = pedidos.stream()
                    .filter(p -> p.getFecha().isAfter(desde) || p.getFecha().isEqual(desde))
                    .collect(Collectors.toList());
        }

        // Filtrar por fecha hasta
        if (fechaHasta != null && !fechaHasta.isEmpty()) {
            LocalDateTime hasta = LocalDate.parse(fechaHasta).atTime(23, 59, 59);
            pedidos = pedidos.stream()
                    .filter(p -> p.getFecha().isBefore(hasta) || p.getFecha().isEqual(hasta))
                    .collect(Collectors.toList());
        }

        return pedidos;
    }
}
