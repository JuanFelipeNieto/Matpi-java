package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.MateriaPrimaDto;
import com.matpi.dominio.dto.ProductoDto;
import com.matpi.persistencia.entity.MateriaPrimaEntity;
import com.matpi.persistencia.entity.ProductoEntity;
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
public class ProductoMapperImpl implements ProductoMapper {

    @Autowired
    private MateriaPrimaMapper materiaPrimaMapper;

    @Override
    public ProductoDto toProductoDto(ProductoEntity producto) {
        if ( producto == null ) {
            return null;
        }

        ProductoDto productoDto = new ProductoDto();

        productoDto.setId( producto.getId() );
        productoDto.setNombreProducto( producto.getNombreProducto() );
        productoDto.setDescripcion( producto.getDescripcion() );
        productoDto.setCantidad( producto.getCantidad() );
        productoDto.setValor( producto.getValor() );
        productoDto.setCategoria( producto.getCategoria() );
        productoDto.setImagen( producto.getImagen() );
        productoDto.setMateriasPrimas( materiaPrimaMapper.toMateriaPrimaDtos( producto.getMateriasPrimas() ) );

        return productoDto;
    }

    @Override
    public List<ProductoDto> toProductoDtos(List<ProductoEntity> productos) {
        if ( productos == null ) {
            return null;
        }

        List<ProductoDto> list = new ArrayList<ProductoDto>( productos.size() );
        for ( ProductoEntity productoEntity : productos ) {
            list.add( toProductoDto( productoEntity ) );
        }

        return list;
    }

    @Override
    public ProductoEntity toProductoEntity(ProductoDto productoDto) {
        if ( productoDto == null ) {
            return null;
        }

        ProductoEntity productoEntity = new ProductoEntity();

        productoEntity.setId( productoDto.getId() );
        productoEntity.setNombreProducto( productoDto.getNombreProducto() );
        productoEntity.setDescripcion( productoDto.getDescripcion() );
        productoEntity.setCantidad( productoDto.getCantidad() );
        productoEntity.setValor( productoDto.getValor() );
        productoEntity.setCategoria( productoDto.getCategoria() );
        productoEntity.setImagen( productoDto.getImagen() );
        productoEntity.setMateriasPrimas( materiaPrimaDtoListToMateriaPrimaEntityList( productoDto.getMateriasPrimas() ) );

        return productoEntity;
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
