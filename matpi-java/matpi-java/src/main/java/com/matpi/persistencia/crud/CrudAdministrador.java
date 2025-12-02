package com.matpi.persistencia.crud;

import com.matpi.persistencia.entity.AdministradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudAdministrador extends JpaRepository<AdministradorEntity, Long> {
}
