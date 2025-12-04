package com.matpi.persistencia.crud;

import com.matpi.persistencia.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudPedido extends JpaRepository<PedidoEntity, Long> {

    // Consultas personalizadas para reportes
    List<PedidoEntity> findByEstado(String estado);

    List<PedidoEntity> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    @Query("SELECT p FROM PedidoEntity p WHERE p.fecha >= :fechaInicio AND p.fecha <= :fechaFin AND p.estado = :estado")
    List<PedidoEntity> findByFechaAndEstado(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin,
            @Param("estado") String estado);

    @Query("SELECT COUNT(p) FROM PedidoEntity p WHERE p.estado = :estado")
    Long countByEstado(@Param("estado") String estado);

    @Query("SELECT SUM(p.valor) FROM PedidoEntity p WHERE p.fecha >= :fechaInicio AND p.fecha <= :fechaFin")
    java.math.BigDecimal sumValorByFechaBetween(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin);
}
