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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class MateriaPrimaRepositorioImpl implements MateriaPrimaRepositorio {

    @Autowired
    private CrudMateriaPrima crudMateriaPrima;

    @Autowired
    private MateriaPrimaMapper mapper;

    @Override
    public List<MateriaPrimaDto> getAll() {
        List<MateriaPrimaEntity> materiasPrimas = StreamSupport
                .stream(crudMateriaPrima.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.toMateriaPrimaDtos(materiasPrimas);
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

    @Override
    public List<MateriaPrimaDto> findAll() {
        return getAll();
    }

    @Override
    public Optional<MateriaPrimaDto> findById(Long id) {
        return getMateriaPrima(id);
    }
}
