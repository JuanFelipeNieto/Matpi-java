package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.PedidoProductoDto;
import com.matpi.persistencia.entity.PedidoProductoEntity;
import com.matpi.persistencia.entity.ProductoEntity;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-02T07:36:08-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class PedidoProductoMapperImpl implements PedidoProductoMapper {

    @Override
    public PedidoProductoDto toPedidoProductoDto(PedidoProductoEntity pedidoProducto) {
        if ( pedidoProducto == null ) {
            return null;
        }

        PedidoProductoDto pedidoProductoDto = new PedidoProductoDto();

        pedidoProductoDto.setProductoId( pedidoProductoProductoId( pedidoProducto ) );
        pedidoProductoDto.setNombreProducto( pedidoProductoProductoNombreProducto( pedidoProducto ) );
        pedidoProductoDto.setCantidad( pedidoProducto.getCantidad() );
        pedidoProductoDto.setPrecioUnitario( pedidoProductoProductoValor( pedidoProducto ) );

        return pedidoProductoDto;
    }

    @Override
    public PedidoProductoEntity toPedidoProductoEntity(PedidoProductoDto pedidoProductoDto) {
        if ( pedidoProductoDto == null ) {
            return null;
        }

        PedidoProductoEntity pedidoProductoEntity = new PedidoProductoEntity();

        pedidoProductoEntity.setCantidad( pedidoProductoDto.getCantidad() );

        return pedidoProductoEntity;
    }

    private Long pedidoProductoProductoId(PedidoProductoEntity pedidoProductoEntity) {
        if ( pedidoProductoEntity == null ) {
            return null;
        }
        ProductoEntity producto = pedidoProductoEntity.getProducto();
        if ( producto == null ) {
            return null;
        }
        Long id = producto.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String pedidoProductoProductoNombreProducto(PedidoProductoEntity pedidoProductoEntity) {
        if ( pedidoProductoEntity == null ) {
            return null;
        }
        ProductoEntity producto = pedidoProductoEntity.getProducto();
        if ( producto == null ) {
            return null;
        }
        String nombreProducto = producto.getNombreProducto();
        if ( nombreProducto == null ) {
            return null;
        }
        return nombreProducto;
    }

    private BigDecimal pedidoProductoProductoValor(PedidoProductoEntity pedidoProductoEntity) {
        if ( pedidoProductoEntity == null ) {
            return null;
        }
        ProductoEntity producto = pedidoProductoEntity.getProducto();
        if ( producto == null ) {
            return null;
        }
        BigDecimal valor = producto.getValor();
        if ( valor == null ) {
            return null;
        }
        return valor;
    }
}
