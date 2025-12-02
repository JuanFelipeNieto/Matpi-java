package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.UsuarioDto;
import com.matpi.persistencia.entity.Rol;
import com.matpi.persistencia.entity.UsuarioEntity;
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
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDto toUsuarioDto(UsuarioEntity usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setId( usuario.getId() );
        usuarioDto.setTelefono( usuario.getTelefono() );
        usuarioDto.setContrasena( usuario.getContrasena() );
        usuarioDto.setCorreoElectronico( usuario.getCorreoElectronico() );
        if ( usuario.getRol() != null ) {
            usuarioDto.setRol( usuario.getRol().name() );
        }
        usuarioDto.setFechaNacimiento( usuario.getFechaNacimiento() );
        usuarioDto.setNombreCompleto( usuario.getNombreCompleto() );
        usuarioDto.setEstado( usuario.getEstado() );
        usuarioDto.setDireccion( usuario.getDireccion() );
        usuarioDto.setFechaIngreso( usuario.getFechaIngreso() );
        usuarioDto.setExperienciaLaboral( usuario.getExperienciaLaboral() );
        usuarioDto.setPassword( usuario.getPassword() );

        return usuarioDto;
    }

    @Override
    public List<UsuarioDto> toUsuarioDtos(List<UsuarioEntity> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<UsuarioDto> list = new ArrayList<UsuarioDto>( usuarios.size() );
        for ( UsuarioEntity usuarioEntity : usuarios ) {
            list.add( toUsuarioDto( usuarioEntity ) );
        }

        return list;
    }

    @Override
    public UsuarioEntity toUsuarioEntity(UsuarioDto usuarioDto) {
        if ( usuarioDto == null ) {
            return null;
        }

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setId( usuarioDto.getId() );
        usuarioEntity.setTelefono( usuarioDto.getTelefono() );
        usuarioEntity.setContrasena( usuarioDto.getContrasena() );
        usuarioEntity.setCorreoElectronico( usuarioDto.getCorreoElectronico() );
        if ( usuarioDto.getRol() != null ) {
            usuarioEntity.setRol( Enum.valueOf( Rol.class, usuarioDto.getRol() ) );
        }
        usuarioEntity.setFechaNacimiento( usuarioDto.getFechaNacimiento() );
        usuarioEntity.setNombreCompleto( usuarioDto.getNombreCompleto() );
        usuarioEntity.setEstado( usuarioDto.getEstado() );
        usuarioEntity.setDireccion( usuarioDto.getDireccion() );
        usuarioEntity.setFechaIngreso( usuarioDto.getFechaIngreso() );
        usuarioEntity.setExperienciaLaboral( usuarioDto.getExperienciaLaboral() );

        return usuarioEntity;
    }
}
