package com.matpi.dominio.servicios;

import com.matpi.dominio.dto.PedidoDto;
import com.matpi.dominio.repositorio.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
