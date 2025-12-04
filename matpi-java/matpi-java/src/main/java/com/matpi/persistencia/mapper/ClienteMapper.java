package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.ClienteDto;
import com.matpi.persistencia.entity.ClienteEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombreCompleto", target = "nombreCompleto"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "ultimaVisita", target = "ultimaVisita"),
            @Mapping(source = "totalConsumo", target = "totalConsumo"),
            @Mapping(source = "fechaRegistro", target = "fechaRegistro"),
            @Mapping(source = "empleado.idUsr", target = "idEmpleado")
    })
    ClienteDto toClienteDto(ClienteEntity cliente);

    List<ClienteDto> toClienteDtos(List<ClienteEntity> clientes);

    @InheritInverseConfiguration
    @Mapping(target = "empleado", ignore = true) // Se debe asignar manualmente en el servicio/repo
    ClienteEntity toClienteEntity(ClienteDto clienteDto);
}
