package com.matpi.persistencia.crud;

import com.matpi.persistencia.entity.MateriaPrimaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudMateriaPrima extends JpaRepository<MateriaPrimaEntity, Long> {
}
