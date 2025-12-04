package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.ProveedorDto;
import com.matpi.persistencia.entity.ProveedorEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { MateriaPrimaMapper.class })
public interface ProveedorMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombreEmpresa", target = "nombreEmpresa"),
            @Mapping(source = "contactoNombre", target = "contactoNombre"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "materiasPrimas", target = "materiasPrimas")
    })
    ProveedorDto toProveedorDto(ProveedorEntity proveedor);

    List<ProveedorDto> toProveedorDtos(List<ProveedorEntity> proveedores);

    @InheritInverseConfiguration
    ProveedorEntity toProveedorEntity(ProveedorDto proveedorDto);
}
