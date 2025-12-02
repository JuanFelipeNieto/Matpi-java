package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.MateriaPrimaDto;
import com.matpi.persistencia.entity.MateriaPrimaEntity;
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
public class MateriaPrimaMapperImpl implements MateriaPrimaMapper {

    @Override
    public MateriaPrimaDto toMateriaPrimaDto(MateriaPrimaEntity materiaPrima) {
        if ( materiaPrima == null ) {
            return null;
        }

        MateriaPrimaDto materiaPrimaDto = new MateriaPrimaDto();

        materiaPrimaDto.setId( materiaPrima.getId() );
        materiaPrimaDto.setNombre( materiaPrima.getNombre() );
        materiaPrimaDto.setCantidadDisponible( materiaPrima.getCantidadDisponible() );
        materiaPrimaDto.setUnidadMedida( materiaPrima.getUnidadMedida() );

        return materiaPrimaDto;
    }

    @Override
    public List<MateriaPrimaDto> toMateriaPrimaDtos(List<MateriaPrimaEntity> materiasPrimas) {
        if ( materiasPrimas == null ) {
            return null;
        }

        List<MateriaPrimaDto> list = new ArrayList<MateriaPrimaDto>( materiasPrimas.size() );
        for ( MateriaPrimaEntity materiaPrimaEntity : materiasPrimas ) {
            list.add( toMateriaPrimaDto( materiaPrimaEntity ) );
        }

        return list;
    }

    @Override
    public MateriaPrimaEntity toMateriaPrimaEntity(MateriaPrimaDto materiaPrimaDto) {
        if ( materiaPrimaDto == null ) {
            return null;
        }

        MateriaPrimaEntity materiaPrimaEntity = new MateriaPrimaEntity();

        materiaPrimaEntity.setId( materiaPrimaDto.getId() );
        materiaPrimaEntity.setNombre( materiaPrimaDto.getNombre() );
        materiaPrimaEntity.setCantidadDisponible( materiaPrimaDto.getCantidadDisponible() );
        materiaPrimaEntity.setUnidadMedida( materiaPrimaDto.getUnidadMedida() );

        return materiaPrimaEntity;
    }
}
