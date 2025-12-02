package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.PedidoProductoDto;
import com.matpi.persistencia.entity.PedidoProductoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PedidoProductoMapper {

    @Mappings({
            @Mapping(source = "producto.id", target = "productoId"),
            @Mapping(source = "producto.nombreProducto", target = "nombreProducto"),
            @Mapping(source = "cantidad", target = "cantidad"),
            @Mapping(source = "producto.valor", target = "precioUnitario")
    })
    PedidoProductoDto toPedidoProductoDto(PedidoProductoEntity pedidoProducto);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pedido", ignore = true)
    @Mapping(target = "producto", ignore = true) // Se debe asignar manualmente
    PedidoProductoEntity toPedidoProductoEntity(PedidoProductoDto pedidoProductoDto);
}
