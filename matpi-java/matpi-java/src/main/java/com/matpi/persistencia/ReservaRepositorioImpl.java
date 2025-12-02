package com.matpi.persistencia;

import com.matpi.dominio.dto.ReservaDto;
import com.matpi.dominio.repositorio.ReservaRepositorio;
import com.matpi.persistencia.crud.CrudReserva;
import com.matpi.persistencia.entity.ReservaEntity;
import com.matpi.persistencia.mapper.ReservaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservaRepositorioImpl implements ReservaRepositorio {

    @Autowired
    private CrudReserva crudReserva;

    @Autowired
    private ReservaMapper mapper;

    @Override
    public List<ReservaDto> getAll() {
        return mapper.toReservaDtos(crudReserva.findAll());
    }

    @Override
    public Optional<ReservaDto> getReserva(Long id) {
        return crudReserva.findById(id).map(reserva -> mapper.toReservaDto(reserva));
    }

    @Override
    public ReservaDto save(ReservaDto reservaDto) {
        ReservaEntity reserva = mapper.toReservaEntity(reservaDto);
        return mapper.toReservaDto(crudReserva.save(reserva));
    }

    @Override
    public void delete(Long id) {
        crudReserva.deleteById(id);
    }
}
