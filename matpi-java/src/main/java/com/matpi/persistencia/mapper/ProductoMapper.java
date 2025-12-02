package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.ProductoDto;
import com.matpi.persistencia.entity.ProductoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { MateriaPrimaMapper.class })
public interface ProductoMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombreProducto", target = "nombreProducto"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "cantidad", target = "cantidad"),
            @Mapping(source = "valor", target = "valor"),
            @Mapping(source = "categoria", target = "categoria"),
            @Mapping(source = "imagen", target = "imagen"),
            @Mapping(source = "materiasPrimas", target = "materiasPrimas")
    })
    ProductoDto toProductoDto(ProductoEntity producto);

    List<ProductoDto> toProductoDtos(List<ProductoEntity> productos);

    @InheritInverseConfiguration
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProductoEntity toProductoEntity(ProductoDto productoDto);
}
