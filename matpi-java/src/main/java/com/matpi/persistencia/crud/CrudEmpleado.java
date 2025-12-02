package com.matpi.persistencia.crud;

import com.matpi.persistencia.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudEmpleado extends JpaRepository<EmpleadoEntity, String> {
}
