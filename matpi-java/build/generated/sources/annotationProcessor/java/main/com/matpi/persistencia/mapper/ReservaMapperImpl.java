package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.ReservaDto;
import com.matpi.persistencia.entity.ClienteEntity;
import com.matpi.persistencia.entity.ReservaEntity;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-02T07:36:08-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class ReservaMapperImpl implements ReservaMapper {

    @Override
    public ReservaDto toReservaDto(ReservaEntity reserva) {
        if ( reserva == null ) {
            return null;
        }

        ReservaDto reservaDto = new ReservaDto();

        reservaDto.setId( reserva.getId() );
        reservaDto.setFechaReserva( reserva.getFechaReserva() );
        reservaDto.setNumeroPersonas( reserva.getNumeroPersonas() );
        reservaDto.setMesa( reserva.getMesa() );
        reservaDto.setEstado( reserva.getEstado() );
        reservaDto.setObservaciones( reserva.getObservaciones() );
        BigInteger id = reservaClienteId( reserva );
        if ( id != null ) {
            reservaDto.setIdCliente( id.toString() );
        }
        reservaDto.setNombreCliente( reservaClienteNombreCompleto( reserva ) );

        return reservaDto;
    }

    @Override
    public List<ReservaDto> toReservaDtos(List<ReservaEntity> reservas) {
        if ( reservas == null ) {
            return null;
        }

        List<ReservaDto> list = new ArrayList<ReservaDto>( reservas.size() );
        for ( ReservaEntity reservaEntity : reservas ) {
            list.add( toReservaDto( reservaEntity ) );
        }

        return list;
    }

    @Override
    public ReservaEntity toReservaEntity(ReservaDto reservaDto) {
        if ( reservaDto == null ) {
            return null;
        }

        ReservaEntity reservaEntity = new ReservaEntity();

        reservaEntity.setId( reservaDto.getId() );
        reservaEntity.setFechaReserva( reservaDto.getFechaReserva() );
        reservaEntity.setNumeroPersonas( reservaDto.getNumeroPersonas() );
        reservaEntity.setMesa( reservaDto.getMesa() );
        reservaEntity.setEstado( reservaDto.getEstado() );
        reservaEntity.setObservaciones( reservaDto.getObservaciones() );

        return reservaEntity;
    }

    private BigInteger reservaClienteId(ReservaEntity reservaEntity) {
        if ( reservaEntity == null ) {
            return null;
        }
        ClienteEntity cliente = reservaEntity.getCliente();
        if ( cliente == null ) {
            return null;
        }
        BigInteger id = cliente.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String reservaClienteNombreCompleto(ReservaEntity reservaEntity) {
        if ( reservaEntity == null ) {
            return null;
        }
        ClienteEntity cliente = reservaEntity.getCliente();
        if ( cliente == null ) {
            return null;
        }
        String nombreCompleto = cliente.getNombreCompleto();
        if ( nombreCompleto == null ) {
            return null;
        }
        return nombreCompleto;
    }
}
