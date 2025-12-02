package com.matpi.dominio.servicios;

import com.matpi.dominio.dto.MateriaPrimaDto;
import com.matpi.dominio.repositorio.MateriaPrimaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaPrimaService {

    @Autowired
    private MateriaPrimaRepositorio materiaPrimaRepositorio;

    public List<MateriaPrimaDto> getAll() {
        return materiaPrimaRepositorio.getAll();
    }

    public Optional<MateriaPrimaDto> getMateriaPrima(Long id) {
        return materiaPrimaRepositorio.getMateriaPrima(id);
    }

    public MateriaPrimaDto save(MateriaPrimaDto materiaPrimaDto) {
        return materiaPrimaRepositorio.save(materiaPrimaDto);
    }

    public boolean delete(Long id) {
        return getMateriaPrima(id).map(mp -> {
            materiaPrimaRepositorio.delete(id);
            return true;
        }).orElse(false);
    }
}
