package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.EmpleadoDto;
import com.matpi.persistencia.entity.EmpleadoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-02T07:36:08-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class EmpleadoMapperImpl implements EmpleadoMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public EmpleadoDto toEmpleadoDto(EmpleadoEntity empleado) {
        if ( empleado == null ) {
            return null;
        }

        EmpleadoDto empleadoDto = new EmpleadoDto();

        empleadoDto.setIdUsr( empleado.getIdUsr() );
        empleadoDto.setEps( empleado.getEps() );
        empleadoDto.setTipoContrato( empleado.getTipoContrato() );
        empleadoDto.setContactoEmergenciaNombre( empleado.getContactoEmergenciaNombre() );
        empleadoDto.setContactoEmergenciaParentesco( empleado.getContactoEmergenciaParentesco() );
        empleadoDto.setContactoEmergenciaNumero( empleado.getContactoEmergenciaNumero() );
        empleadoDto.setFechaTerminacionContrato( empleado.getFechaTerminacionContrato() );
        empleadoDto.setUsuario( usuarioMapper.toUsuarioDto( empleado.getUsuario() ) );
        empleadoDto.setId( empleado.getId() );

        return empleadoDto;
    }

    @Override
    public List<EmpleadoDto> toEmpleadoDtos(List<EmpleadoEntity> empleados) {
        if ( empleados == null ) {
            return null;
        }

        List<EmpleadoDto> list = new ArrayList<EmpleadoDto>( empleados.size() );
        for ( EmpleadoEntity empleadoEntity : empleados ) {
            list.add( toEmpleadoDto( empleadoEntity ) );
        }

        return list;
    }

    @Override
    public EmpleadoEntity toEmpleadoEntity(EmpleadoDto empleadoDto) {
        if ( empleadoDto == null ) {
            return null;
        }

        EmpleadoEntity empleadoEntity = new EmpleadoEntity();

        empleadoEntity.setIdUsr( empleadoDto.getIdUsr() );
        empleadoEntity.setEps( empleadoDto.getEps() );
        empleadoEntity.setTipoContrato( empleadoDto.getTipoContrato() );
        empleadoEntity.setContactoEmergenciaNombre( empleadoDto.getContactoEmergenciaNombre() );
        empleadoEntity.setContactoEmergenciaParentesco( empleadoDto.getContactoEmergenciaParentesco() );
        empleadoEntity.setContactoEmergenciaNumero( empleadoDto.getContactoEmergenciaNumero() );
        empleadoEntity.setFechaTerminacionContrato( empleadoDto.getFechaTerminacionContrato() );
        empleadoEntity.setUsuario( usuarioMapper.toUsuarioEntity( empleadoDto.getUsuario() ) );

        return empleadoEntity;
    }
}
