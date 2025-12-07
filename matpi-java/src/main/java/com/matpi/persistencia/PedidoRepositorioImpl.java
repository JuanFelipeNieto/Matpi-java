package com.matpi.persistencia;

import com.matpi.dominio.dto.PedidoDto;
import com.matpi.dominio.repositorio.PedidoRepositorio;
import com.matpi.persistencia.crud.CrudPedido;
import com.matpi.persistencia.entity.PedidoEntity;
import com.matpi.persistencia.mapper.PedidoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class PedidoRepositorioImpl implements PedidoRepositorio {

    @Autowired
    private CrudPedido crudPedido;

    @Autowired
    private PedidoMapper mapper;

    @Override
    public List<PedidoDto> getAll() {
        return mapper.toPedidoDtos(crudPedido.findAll());
    }

    @Override
    public Optional<PedidoDto> getPedido(Long id) {
        return crudPedido.findById(id).map(pedido -> mapper.toPedidoDto(pedido));
    }

    @Override
    public PedidoDto save(PedidoDto pedidoDto) {
        PedidoEntity pedido = mapper.toPedidoEntity(pedidoDto);
        return mapper.toPedidoDto(crudPedido.save(pedido));
    }

    @Override
    public void delete(Long id) {
        crudPedido.deleteById(id);
    }

    @Override
    public List<PedidoDto> getPedidosPorEstado(String estado) {
        return mapper.toPedidoDtos(crudPedido.findByEstado(estado));
    }

    @Override
    public List<PedidoDto> getPedidosPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return mapper.toPedidoDtos(crudPedido.findByFechaBetween(fechaInicio, fechaFin));
    }

    @Override
    public List<PedidoDto> getPedidosPorFechaYEstado(LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado) {
        return mapper.toPedidoDtos(crudPedido.findByFechaAndEstado(fechaInicio, fechaFin, estado));
    }

    @Override
    public Long contarPedidosPorEstado(String estado) {
        return crudPedido.countByEstado(estado);
    }

    @Override
    public BigDecimal calcularTotalVentas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        BigDecimal total = crudPedido.sumValorByFechaBetween(fechaInicio, fechaFin);
        return total != null ? total : BigDecimal.ZERO;
    }
}
