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

   public materiaprima create (materiaprima){
    return materiaPrimaRepositorio.save(materiaprima);
   }

    public List<MateriaPrimaDto> getAll() {
        return materiaPrimaRepositorio.findAll();
    }

    public Optional<MateriaPrimaDto> findById(Long id) {
        return materiaPrimaRepositorio.findById(id);
    }

    public MateriaPrimaDto save(MateriaPrimaDto materiaPrimaDto) {
        return materiaPrimaRepositorio.save(materiaPrimaDto);
    }

    public void delete(materiaprima){
        materiaPrimaRepositorio.delete(materiaprima);
    }
}
