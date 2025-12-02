package com.matpi.persistencia;

import com.matpi.dominio.dto.MateriaPrimaDto;
import com.matpi.dominio.repositorio.MateriaPrimaRepositorio;
import com.matpi.persistencia.crud.CrudMateriaPrima;
import com.matpi.persistencia.entity.MateriaPrimaEntity;
import com.matpi.persistencia.mapper.MateriaPrimaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MateriaPrimaRepositorioImpl implements MateriaPrimaRepositorio {

    @Autowired
    private CrudMateriaPrima crudMateriaPrima;

    @Autowired
    private MateriaPrimaMapper mapper;

    @Override
    public List<MateriaPrimaDto> getAll() {
        return mapper.toMateriaPrimaDtos(crudMateriaPrima.findAll());
    }

    @Override
    public Optional<MateriaPrimaDto> getMateriaPrima(Long id) {
        return crudMateriaPrima.findById(id).map(mp -> mapper.toMateriaPrimaDto(mp));
    }

    @Override
    public MateriaPrimaDto save(MateriaPrimaDto materiaPrimaDto) {
        MateriaPrimaEntity mp = mapper.toMateriaPrimaEntity(materiaPrimaDto);
        return mapper.toMateriaPrimaDto(crudMateriaPrima.save(mp));
    }

    @Override
    public void delete(Long id) {
        crudMateriaPrima.deleteById(id);
    }
}
