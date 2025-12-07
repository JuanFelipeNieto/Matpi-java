package com.matpi.persistencia.crud;

import com.matpi.persistencia.entity.MateriaPrimaEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudMateriaPrima extends CrudRepository<MateriaPrimaEntity, Long> {
}
