package com.matpi.dominio.repositorio;

import com.matpi.dominio.dto.ProveedorDto;
import java.util.List;
import java.util.Optional;

public interface ProveedorRepositorio {
    List<ProveedorDto> getAll();

    Optional<ProveedorDto> getProveedor(Long id);

    ProveedorDto save(ProveedorDto proveedorDto);

    void delete(Long id);
}
