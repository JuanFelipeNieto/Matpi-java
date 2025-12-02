package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.MateriaPrimaDto;
import com.matpi.dominio.dto.ProveedorDto;
import com.matpi.persistencia.entity.MateriaPrimaEntity;
import com.matpi.persistencia.entity.ProveedorEntity;
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
public class ProveedorMapperImpl implements ProveedorMapper {

    @Autowired
    private MateriaPrimaMapper materiaPrimaMapper;

    @Override
    public ProveedorDto toProveedorDto(ProveedorEntity proveedor) {
        if ( proveedor == null ) {
            return null;
        }

        ProveedorDto proveedorDto = new ProveedorDto();

        proveedorDto.setId( proveedor.getId() );
        proveedorDto.setNombreEmpresa( proveedor.getNombreEmpresa() );
        proveedorDto.setContactoNombre( proveedor.getContactoNombre() );
        proveedorDto.setTelefono( proveedor.getTelefono() );
        proveedorDto.setDireccion( proveedor.getDireccion() );
        proveedorDto.setMateriasPrimas( materiaPrimaMapper.toMateriaPrimaDtos( proveedor.getMateriasPrimas() ) );

        return proveedorDto;
    }

    @Override
    public List<ProveedorDto> toProveedorDtos(List<ProveedorEntity> proveedores) {
        if ( proveedores == null ) {
            return null;
        }

        List<ProveedorDto> list = new ArrayList<ProveedorDto>( proveedores.size() );
        for ( ProveedorEntity proveedorEntity : proveedores ) {
            list.add( toProveedorDto( proveedorEntity ) );
        }

        return list;
    }

    @Override
    public ProveedorEntity toProveedorEntity(ProveedorDto proveedorDto) {
        if ( proveedorDto == null ) {
            return null;
        }

        ProveedorEntity proveedorEntity = new ProveedorEntity();

        proveedorEntity.setId( proveedorDto.getId() );
        proveedorEntity.setNombreEmpresa( proveedorDto.getNombreEmpresa() );
        proveedorEntity.setContactoNombre( proveedorDto.getContactoNombre() );
        proveedorEntity.setTelefono( proveedorDto.getTelefono() );
        proveedorEntity.setDireccion( proveedorDto.getDireccion() );
        proveedorEntity.setMateriasPrimas( materiaPrimaDtoListToMateriaPrimaEntityList( proveedorDto.getMateriasPrimas() ) );

        return proveedorEntity;
    }

    protected List<MateriaPrimaEntity> materiaPrimaDtoListToMateriaPrimaEntityList(List<MateriaPrimaDto> list) {
        if ( list == null ) {
            return null;
        }

        List<MateriaPrimaEntity> list1 = new ArrayList<MateriaPrimaEntity>( list.size() );
        for ( MateriaPrimaDto materiaPrimaDto : list ) {
            list1.add( materiaPrimaMapper.toMateriaPrimaEntity( materiaPrimaDto ) );
        }

        return list1;
    }
}
