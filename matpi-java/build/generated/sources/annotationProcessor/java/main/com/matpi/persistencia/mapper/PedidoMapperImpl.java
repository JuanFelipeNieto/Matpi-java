package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.PedidoDto;
import com.matpi.dominio.dto.PedidoProductoDto;
import com.matpi.persistencia.entity.ClienteEntity;
import com.matpi.persistencia.entity.EmpleadoEntity;
import com.matpi.persistencia.entity.PedidoEntity;
import com.matpi.persistencia.entity.PedidoProductoEntity;
import com.matpi.persistencia.entity.ReservaEntity;
import java.math.BigInteger;
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
public class PedidoMapperImpl implements PedidoMapper {

    @Autowired
    private PedidoProductoMapper pedidoProductoMapper;

    @Override
    public PedidoDto toPedidoDto(PedidoEntity pedido) {
        if ( pedido == null ) {
            return null;
        }

        PedidoDto pedidoDto = new PedidoDto();

        pedidoDto.setId( pedido.getId() );
        pedidoDto.setFecha( pedido.getFecha() );
        pedidoDto.setEstado( pedido.getEstado() );
        pedidoDto.setValor( pedido.getValor() );
        pedidoDto.setMesa( pedido.getMesa() );
        pedidoDto.setNumeroPersonas( pedido.getNumeroPersonas() );
        pedidoDto.setIdEmpleado( pedidoEmpleadoIdUsr( pedido ) );
        BigInteger id = pedidoClienteId( pedido );
        if ( id != null ) {
            pedidoDto.setIdCliente( id.toString() );
        }
        pedidoDto.setIdReserva( pedidoReservaId( pedido ) );
        pedidoDto.setProductos( pedidoProductoEntityListToPedidoProductoDtoList( pedido.getProductos() ) );

        return pedidoDto;
    }

    @Override
    public List<PedidoDto> toPedidoDtos(List<PedidoEntity> pedidos) {
        if ( pedidos == null ) {
            return null;
        }

        List<PedidoDto> list = new ArrayList<PedidoDto>( pedidos.size() );
        for ( PedidoEntity pedidoEntity : pedidos ) {
            list.add( toPedidoDto( pedidoEntity ) );
        }

        return list;
    }

    @Override
    public PedidoEntity toPedidoEntity(PedidoDto pedidoDto) {
        if ( pedidoDto == null ) {
            return null;
        }

        PedidoEntity pedidoEntity = new PedidoEntity();

        pedidoEntity.setId( pedidoDto.getId() );
        pedidoEntity.setFecha( pedidoDto.getFecha() );
        pedidoEntity.setEstado( pedidoDto.getEstado() );
        pedidoEntity.setValor( pedidoDto.getValor() );
        pedidoEntity.setMesa( pedidoDto.getMesa() );
        pedidoEntity.setNumeroPersonas( pedidoDto.getNumeroPersonas() );
        pedidoEntity.setProductos( pedidoProductoDtoListToPedidoProductoEntityList( pedidoDto.getProductos() ) );

        return pedidoEntity;
    }

    private String pedidoEmpleadoIdUsr(PedidoEntity pedidoEntity) {
        if ( pedidoEntity == null ) {
            return null;
        }
        EmpleadoEntity empleado = pedidoEntity.getEmpleado();
        if ( empleado == null ) {
            return null;
        }
        String idUsr = empleado.getIdUsr();
        if ( idUsr == null ) {
            return null;
        }
        return idUsr;
    }

    private BigInteger pedidoClienteId(PedidoEntity pedidoEntity) {
        if ( pedidoEntity == null ) {
            return null;
        }
        ClienteEntity cliente = pedidoEntity.getCliente();
        if ( cliente == null ) {
            return null;
        }
        BigInteger id = cliente.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long pedidoReservaId(PedidoEntity pedidoEntity) {
        if ( pedidoEntity == null ) {
            return null;
        }
        ReservaEntity reserva = pedidoEntity.getReserva();
        if ( reserva == null ) {
            return null;
        }
        Long id = reserva.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<PedidoProductoDto> pedidoProductoEntityListToPedidoProductoDtoList(List<PedidoProductoEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<PedidoProductoDto> list1 = new ArrayList<PedidoProductoDto>( list.size() );
        for ( PedidoProductoEntity pedidoProductoEntity : list ) {
            list1.add( pedidoProductoMapper.toPedidoProductoDto( pedidoProductoEntity ) );
        }

        return list1;
    }

    protected List<PedidoProductoEntity> pedidoProductoDtoListToPedidoProductoEntityList(List<PedidoProductoDto> list) {
        if ( list == null ) {
            return null;
        }

        List<PedidoProductoEntity> list1 = new ArrayList<PedidoProductoEntity>( list.size() );
        for ( PedidoProductoDto pedidoProductoDto : list ) {
            list1.add( pedidoProductoMapper.toPedidoProductoEntity( pedidoProductoDto ) );
        }

        return list1;
    }
}
