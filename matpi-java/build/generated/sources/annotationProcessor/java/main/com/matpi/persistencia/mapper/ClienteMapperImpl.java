package com.matpi.persistencia.mapper;

import com.matpi.dominio.dto.ClienteDto;
import com.matpi.persistencia.entity.ClienteEntity;
import com.matpi.persistencia.entity.EmpleadoEntity;
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
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDto toClienteDto(ClienteEntity cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setId( cliente.getId() );
        clienteDto.setNombreCompleto( cliente.getNombreCompleto() );
        clienteDto.setTelefono( cliente.getTelefono() );
        clienteDto.setUltimaVisita( cliente.getUltimaVisita() );
        clienteDto.setTotalConsumo( cliente.getTotalConsumo() );
        clienteDto.setFechaRegistro( cliente.getFechaRegistro() );
        clienteDto.setIdEmpleado( clienteEmpleadoIdUsr( cliente ) );

        return clienteDto;
    }

    @Override
    public List<ClienteDto> toClienteDtos(List<ClienteEntity> clientes) {
        if ( clientes == null ) {
            return null;
        }

        List<ClienteDto> list = new ArrayList<ClienteDto>( clientes.size() );
        for ( ClienteEntity clienteEntity : clientes ) {
            list.add( toClienteDto( clienteEntity ) );
        }

        return list;
    }

    @Override
    public ClienteEntity toClienteEntity(ClienteDto clienteDto) {
        if ( clienteDto == null ) {
            return null;
        }

        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setId( clienteDto.getId() );
        clienteEntity.setNombreCompleto( clienteDto.getNombreCompleto() );
        clienteEntity.setTelefono( clienteDto.getTelefono() );
        clienteEntity.setUltimaVisita( clienteDto.getUltimaVisita() );
        clienteEntity.setTotalConsumo( clienteDto.getTotalConsumo() );
        clienteEntity.setFechaRegistro( clienteDto.getFechaRegistro() );

        return clienteEntity;
    }

    private String clienteEmpleadoIdUsr(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }
        EmpleadoEntity empleado = clienteEntity.getEmpleado();
        if ( empleado == null ) {
            return null;
        }
        String idUsr = empleado.getIdUsr();
        if ( idUsr == null ) {
            return null;
        }
        return idUsr;
    }
}
