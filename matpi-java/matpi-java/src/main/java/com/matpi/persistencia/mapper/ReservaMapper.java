package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.ReservaDto;
import com.matpi.persistencia.entity.ReservaEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "fechaReserva", target = "fechaReserva"),
            @Mapping(source = "numeroPersonas", target = "numeroPersonas"),
            @Mapping(source = "mesa", target = "mesa"),
            @Mapping(source = "estado", target = "estado"),
            @Mapping(source = "observaciones", target = "observaciones"),
            @Mapping(source = "cliente.id", target = "idCliente"), // Conversión de BigInteger a String puede requerir
                                                                   // mapper manual o toString
            @Mapping(source = "cliente.nombreCompleto", target = "nombreCliente")
    })
    ReservaDto toReservaDto(ReservaEntity reserva);

    List<ReservaDto> toReservaDtos(List<ReservaEntity> reservas);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    ReservaEntity toReservaEntity(ReservaDto reservaDto);

    // Helper para BigInteger a String si es necesario, MapStruct suele manejarlo
}
