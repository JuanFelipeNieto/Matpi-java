package com.matpi.dominio.servicios;

import com.matpi.dominio.dto.ProveedorDto;
import com.matpi.dominio.repositorio.ProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    public List<ProveedorDto> getAll() {
        return proveedorRepositorio.getAll();
    }

    public Optional<ProveedorDto> getProveedor(Long id) {
        return proveedorRepositorio.getProveedor(id);
    }

    public ProveedorDto save(ProveedorDto proveedorDto) {
        return proveedorRepositorio.save(proveedorDto);
    }

    public boolean delete(Long id) {
        return getProveedor(id).map(proveedor -> {
            proveedorRepositorio.delete(id);
            return true;
        }).orElse(false);
    }
}
