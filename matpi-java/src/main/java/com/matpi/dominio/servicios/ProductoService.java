package com.matpi.dominio.servicios;

import com.matpi.dominio.dto.ProductoDto;
import com.matpi.dominio.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    public List<ProductoDto> getAll() {
        return productoRepositorio.getAll();
    }

    public Optional<ProductoDto> getProducto(Long id) {
        return productoRepositorio.getProducto(id);
    }

    public ProductoDto save(ProductoDto productoDto) {
        return productoRepositorio.save(productoDto);
    }

    public boolean delete(Long id) {
        return getProducto(id).map(producto -> {
            productoRepositorio.delete(id);
            return true;
        }).orElse(false);
    }
}
