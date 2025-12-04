package com.matpi.persistencia.crud;

import com.matpi.persistencia.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CrudUsuario extends JpaRepository<UsuarioEntity, String> {
    Optional<UsuarioEntity> findByCorreoElectronico(String correoElectronico);
}
