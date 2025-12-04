package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.EmpleadoDto;
import com.matpi.persistencia.entity.EmpleadoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { UsuarioMapper.class })
public interface EmpleadoMapper {

    @Mappings({
            @Mapping(source = "idUsr", target = "idUsr"),
            @Mapping(source = "eps", target = "eps"),
            @Mapping(source = "tipoContrato", target = "tipoContrato"),
            @Mapping(source = "contactoEmergenciaNombre", target = "contactoEmergenciaNombre"),
            @Mapping(source = "contactoEmergenciaParentesco", target = "contactoEmergenciaParentesco"),
            @Mapping(source = "contactoEmergenciaNumero", target = "contactoEmergenciaNumero"),
            @Mapping(source = "fechaTerminacionContrato", target = "fechaTerminacionContrato"),
            @Mapping(source = "usuario", target = "usuario")
    })
    EmpleadoDto toEmpleadoDto(EmpleadoEntity empleado);

    List<EmpleadoDto> toEmpleadoDtos(List<EmpleadoEntity> empleados);

    @InheritInverseConfiguration
    EmpleadoEntity toEmpleadoEntity(EmpleadoDto empleadoDto);
}
