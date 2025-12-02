package com.matpi.dominio.repositorio;

import com.matpi.dominio.dto.PedidoDto;
import java.util.List;
import java.util.Optional;

public interface PedidoRepositorio {
    List<PedidoDto> getAll();

    Optional<PedidoDto> getPedido(Long id);

    PedidoDto save(PedidoDto pedidoDto);

    void delete(Long id);
}
