package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.PedidoProductoDto;
import com.matpi.persistencia.entity.PedidoProductoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoProductoMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "pedido.id", target = "pedidoId"),
            @Mapping(source = "producto.id", target = "productoId"),
            @Mapping(source = "cantidad", target = "cantidad"),
            @Mapping(source = "producto.nombreProducto", target = "nombreProducto"),
            @Mapping(source = "producto.valor", target = "precioUnitario")
    })
    PedidoProductoDto toPedidoProductoDto(PedidoProductoEntity pedidoProducto);

    List<PedidoProductoDto> toPedidoProductoDtos(List<PedidoProductoEntity> pedidoProductos);

    @InheritInverseConfiguration
    @Mapping(target = "pedido", ignore = true)
    @Mapping(target = "producto", ignore = true)
    PedidoProductoEntity toPedidoProductoEntity(PedidoProductoDto pedidoProductoDto);
}
