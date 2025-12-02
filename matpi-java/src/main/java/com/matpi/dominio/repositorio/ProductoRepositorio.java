package com.matpi.dominio.repositorio;

import com.matpi.dominio.dto.ProductoDto;
import java.util.List;
import java.util.Optional;

public interface ProductoRepositorio {
    List<ProductoDto> getAll();

    Optional<ProductoDto> getProducto(Long id);

    ProductoDto save(ProductoDto productoDto);

    void delete(Long id);
}
