package com.matpi.persistencia;

import com.matpi.dominio.dto.ClienteDto;
import com.matpi.dominio.repositorio.ClienteRepositorio;
import com.matpi.persistencia.crud.CrudCliente;
import com.matpi.persistencia.entity.ClienteEntity;
import com.matpi.persistencia.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepositorioImpl implements ClienteRepositorio {

    @Autowired
    private CrudCliente crudCliente;

    @Autowired
    private ClienteMapper mapper;

    @Override
    public List<ClienteDto> getAll() {
        return mapper.toClienteDtos(crudCliente.findAll());
    }

    @Override
    public Optional<ClienteDto> getCliente(BigInteger id) {
        return crudCliente.findById(id).map(cliente -> mapper.toClienteDto(cliente));
    }

    @Override
    public ClienteDto save(ClienteDto clienteDto) {
        ClienteEntity cliente = mapper.toClienteEntity(clienteDto);
        return mapper.toClienteDto(crudCliente.save(cliente));
    }

    @Override
    public void delete(BigInteger id) {
        crudCliente.deleteById(id);
    }
}
