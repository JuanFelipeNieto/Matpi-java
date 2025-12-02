package com.matpi.persistencia.crud;

import com.matpi.persistencia.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudProveedor extends JpaRepository<ProveedorEntity, Long> {
}
