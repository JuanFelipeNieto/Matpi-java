package com.matpi.persistencia.crud;

import com.matpi.persistencia.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudProducto extends JpaRepository<ProductoEntity, Long> {
}
