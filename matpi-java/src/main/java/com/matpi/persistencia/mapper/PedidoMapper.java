package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.PedidoDto;
import com.matpi.persistencia.entity.PedidoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { PedidoProductoMapper.class })
public interface PedidoMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "fecha", target = "fecha"),
            @Mapping(source = "estado", target = "estado"),
            @Mapping(source = "valor", target = "valor"),
            @Mapping(source = "mesa", target = "mesa"),
            @Mapping(source = "numeroPersonas", target = "numeroPersonas"),
            @Mapping(source = "empleado.idUsr", target = "idEmpleado"),
            @Mapping(source = "cliente.id", target = "idCliente"),
            @Mapping(source = "reserva.id", target = "idReserva"),
            @Mapping(source = "productos", target = "productos")
    })
    PedidoDto toPedidoDto(PedidoEntity pedido);

    List<PedidoDto> toPedidoDtos(List<PedidoEntity> pedidos);

    @InheritInverseConfiguration
    @Mapping(target = "empleado", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "reserva", ignore = true)
    PedidoEntity toPedidoEntity(PedidoDto pedidoDto);
}
