package com.matpi.dominio.repositorio;

import com.matpi.dominio.dto.EmpleadoDto;
import java.util.List;
import java.util.Optional;

public interface EmpleadoRepositorio {
    List<EmpleadoDto> getAll();

    Optional<EmpleadoDto> getEmpleado(String id);

    EmpleadoDto save(EmpleadoDto empleadoDto);

    void delete(String id);
}
