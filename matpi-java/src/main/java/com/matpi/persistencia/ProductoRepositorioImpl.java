package com.matpi.persistencia;

import com.matpi.dominio.dto.ProductoDto;
import com.matpi.dominio.repositorio.ProductoRepositorio;
import com.matpi.persistencia.crud.CrudProducto;
import com.matpi.persistencia.entity.ProductoEntity;
import com.matpi.persistencia.mapper.ProductoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepositorioImpl implements ProductoRepositorio {

    @Autowired
    private CrudProducto crudProducto;

    @Autowired
    private ProductoMapper mapper;

    @Override
    public List<ProductoDto> getAll() {
        return mapper.toProductoDtos(crudProducto.findAll());
    }

    @Override
    public Optional<ProductoDto> getProducto(Long id) {
        return crudProducto.findById(id).map(producto -> mapper.toProductoDto(producto));
    }

    @Override
    public ProductoDto save(ProductoDto productoDto) {
        ProductoEntity producto = mapper.toProductoEntity(productoDto);
        return mapper.toProductoDto(crudProducto.save(producto));
    }

    @Override
    public void delete(Long id) {
        crudProducto.deleteById(id);
    }
}
