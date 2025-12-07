package com.matpi.persistencia.crud;

import com.matpi.persistencia.entity.PedidoProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrudPedidoProducto extends JpaRepository<PedidoProductoEntity, Long> {
    List<PedidoProductoEntity> findByPedidoId(Long pedidoId);
}
