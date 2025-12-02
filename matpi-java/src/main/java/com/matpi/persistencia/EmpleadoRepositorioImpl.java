package com.matpi.persistencia;

import com.matpi.dominio.dto.EmpleadoDto;
import com.matpi.dominio.repositorio.EmpleadoRepositorio;
import com.matpi.persistencia.crud.CrudEmpleado;
import com.matpi.persistencia.crud.CrudUsuario;
import com.matpi.persistencia.entity.EmpleadoEntity;
import com.matpi.persistencia.mapper.EmpleadoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmpleadoRepositorioImpl implements EmpleadoRepositorio {

    @Autowired
    private CrudEmpleado crudEmpleado;

    @Autowired
    private CrudUsuario crudUsuario;

    @Autowired
    private EmpleadoMapper mapper;

    @Override
    public List<EmpleadoDto> getAll() {
        return mapper.toEmpleadoDtos(crudEmpleado.findAll());
    }

    @Override
    public Optional<EmpleadoDto> getEmpleado(String id) {
        return crudEmpleado.findById(id).map(empleado -> mapper.toEmpleadoDto(empleado));
    }

    @Override
    public EmpleadoDto save(EmpleadoDto empleadoDto) {
        EmpleadoEntity empleado = mapper.toEmpleadoEntity(empleadoDto);

        // Obtener la referencia al Usuario guardado
        if (empleadoDto.getIdUsr() != null) {
            crudUsuario.findById(empleadoDto.getIdUsr()).ifPresent(usuario -> {
                empleado.setUsuario(usuario);
                empleado.setIdUsr(usuario.getId());
            });
        }

        return mapper.toEmpleadoDto(crudEmpleado.save(empleado));
    }

    @Override
    public void delete(String id) {
        crudEmpleado.deleteById(id);
    }
}
