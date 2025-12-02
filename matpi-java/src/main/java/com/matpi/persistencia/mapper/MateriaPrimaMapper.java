package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.MateriaPrimaDto;
import com.matpi.persistencia.entity.MateriaPrimaEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MateriaPrimaMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "cantidadDisponible", target = "cantidadDisponible"),
            @Mapping(source = "unidadMedida", target = "unidadMedida")
    })
    MateriaPrimaDto toMateriaPrimaDto(MateriaPrimaEntity materiaPrima);

    List<MateriaPrimaDto> toMateriaPrimaDtos(List<MateriaPrimaEntity> materiasPrimas);

    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    @Mapping(target = "proveedores", ignore = true)
    MateriaPrimaEntity toMateriaPrimaEntity(MateriaPrimaDto materiaPrimaDto);
}
