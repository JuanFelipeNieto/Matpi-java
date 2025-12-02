package com.matpi.dominio.repositorio;

import com.matpi.dominio.dto.ReservaDto;
import java.util.List;
import java.util.Optional;

public interface ReservaRepositorio {
    List<ReservaDto> getAll();

    Optional<ReservaDto> getReserva(Long id);

    ReservaDto save(ReservaDto reservaDto);

    void delete(Long id);
}
