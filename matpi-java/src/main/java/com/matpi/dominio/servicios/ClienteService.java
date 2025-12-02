package com.matpi.dominio.servicios;

import com.matpi.dominio.dto.ClienteDto;
import com.matpi.dominio.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<ClienteDto> getAll() {
        return clienteRepositorio.getAll();
    }

    public Optional<ClienteDto> getCliente(BigInteger id) {
        return clienteRepositorio.getCliente(id);
    }

    public ClienteDto save(ClienteDto clienteDto) {
        return clienteRepositorio.save(clienteDto);
    }

    public boolean delete(BigInteger id) {
        return getCliente(id).map(cliente -> {
            clienteRepositorio.delete(id);
            return true;
        }).orElse(false);
    }
}
