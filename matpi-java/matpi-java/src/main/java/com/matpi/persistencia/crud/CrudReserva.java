package com.matpi.persistencia.crud;

import com.matpi.persistencia.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudReserva extends JpaRepository<ReservaEntity, Long> {
}
