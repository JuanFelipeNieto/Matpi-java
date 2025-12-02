package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.UsuarioDto;
import com.matpi.persistencia.entity.UsuarioEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "contrasena", target = "contrasena"),
            @Mapping(source = "correoElectronico", target = "correoElectronico"),
            @Mapping(source = "rol", target = "rol"), // Enum to String conversion is automatic usually
            @Mapping(source = "fechaNacimiento", target = "fechaNacimiento"),
            @Mapping(source = "nombreCompleto", target = "nombreCompleto"),
            @Mapping(source = "estado", target = "estado"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "fechaIngreso", target = "fechaIngreso"),
            @Mapping(source = "experienciaLaboral", target = "experienciaLaboral")
    })
    UsuarioDto toUsuarioDto(UsuarioEntity usuario);

    List<UsuarioDto> toUsuarioDtos(List<UsuarioEntity> usuarios);

    @InheritInverseConfiguration
    UsuarioEntity toUsuarioEntity(UsuarioDto usuarioDto);
}
