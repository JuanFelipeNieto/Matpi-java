package com.matpi.persistencia.crud;

import com.matpi.persistencia.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudPedido extends JpaRepository<PedidoEntity, Long> {
}
