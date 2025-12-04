package com.matpi.dominio.repositorio;

import com.matpi.dominio.dto.UsuarioDto;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorio {
    List<UsuarioDto> getAll();

    Optional<UsuarioDto> getUsuario(String id);

    UsuarioDto save(UsuarioDto usuarioDto);

    void delete(String id);

    Optional<UsuarioDto> getByCorreo(String correo);
}
