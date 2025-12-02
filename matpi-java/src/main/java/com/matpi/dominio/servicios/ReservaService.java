package com.matpi.dominio.servicios;

import com.matpi.dominio.dto.ReservaDto;
import com.matpi.dominio.repositorio.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    public List<ReservaDto> getAll() {
        return reservaRepositorio.getAll();
    }

    public Optional<ReservaDto> getReserva(Long id) {
        return reservaRepositorio.getReserva(id);
    }

    public ReservaDto save(ReservaDto reservaDto) {
        return reservaRepositorio.save(reservaDto);
    }

    public boolean delete(Long id) {
        return getReserva(id).map(reserva -> {
            reservaRepositorio.delete(id);
            return true;
        }).orElse(false);
    }
}
