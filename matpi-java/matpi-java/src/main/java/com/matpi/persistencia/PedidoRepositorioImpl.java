package com.matpi.persistencia;

import com.matpi.dominio.dto.PedidoDto;
import com.matpi.dominio.repositorio.PedidoRepositorio;
import com.matpi.persistencia.crud.CrudPedido;
import com.matpi.persistencia.entity.PedidoEntity;
import com.matpi.persistencia.mapper.PedidoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
