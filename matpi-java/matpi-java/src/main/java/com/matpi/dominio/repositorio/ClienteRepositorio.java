package com.matpi.dominio.repositorio;

import com.matpi.dominio.dto.ClienteDto;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ClienteRepositorio {
    List<ClienteDto> getAll();

    Optional<ClienteDto> getCliente(BigInteger id);

    ClienteDto save(ClienteDto clienteDto);

    void delete(BigInteger id);
}
