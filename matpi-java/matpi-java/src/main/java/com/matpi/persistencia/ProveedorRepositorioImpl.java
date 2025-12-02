package com.matpi.persistencia;

import com.matpi.dominio.dto.ProveedorDto;
import com.matpi.dominio.repositorio.ProveedorRepositorio;
import com.matpi.persistencia.crud.CrudProveedor;
import com.matpi.persistencia.entity.ProveedorEntity;
import com.matpi.persistencia.mapper.ProveedorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProveedorRepositorioImpl implements ProveedorRepositorio {

    @Autowired
    private CrudProveedor crudProveedor;

    @Autowired
    private ProveedorMapper mapper;

    @Override
    public List<ProveedorDto> getAll() {
        return mapper.toProveedorDtos(crudProveedor.findAll());
    }

    @Override
    public Optional<ProveedorDto> getProveedor(Long id) {
        return crudProveedor.findById(id).map(proveedor -> mapper.toProveedorDto(proveedor));
    }

    @Override
    public ProveedorDto save(ProveedorDto proveedorDto) {
        ProveedorEntity proveedor = mapper.toProveedorEntity(proveedorDto);
        return mapper.toProveedorDto(crudProveedor.save(proveedor));
    }

    @Override
    public void delete(Long id) {
        crudProveedor.deleteById(id);
    }
}
