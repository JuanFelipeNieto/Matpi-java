package com.matpi.dominio.repositorio;

import com.matpi.dominio.dto.MateriaPrimaDto;
import java.util.List;
import java.util.Optional;

public interface MateriaPrimaRepositorio {
    List<MateriaPrimaDto> getAll();

    Optional<MateriaPrimaDto> getMateriaPrima(Long id);

    MateriaPrimaDto save(MateriaPrimaDto materiaPrimaDto);

    void delete(Long id);
}
