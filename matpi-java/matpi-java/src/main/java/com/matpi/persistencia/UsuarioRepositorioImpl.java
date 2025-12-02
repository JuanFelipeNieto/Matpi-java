package com.matpi.persistencia;

import com.matpi.dominio.dto.UsuarioDto;
import com.matpi.dominio.repositorio.UsuarioRepositorio;
import com.matpi.persistencia.crud.CrudUsuario;
import com.matpi.persistencia.entity.UsuarioEntity;
import com.matpi.persistencia.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositorioImpl implements UsuarioRepositorio {

    @Autowired
    private CrudUsuario crudUsuario;

    @Autowired
    private UsuarioMapper mapper;

    @Override
    public List<UsuarioDto> getAll() {
        List<UsuarioEntity> usuarios = crudUsuario.findAll();
        return mapper.toUsuarioDtos(usuarios);
    }

    @Override
    public Optional<UsuarioDto> getUsuario(String id) {
        return crudUsuario.findById(id).map(usuario -> mapper.toUsuarioDto(usuario));
    }

    @Override
    public UsuarioDto save(UsuarioDto usuarioDto) {
        UsuarioEntity usuario = mapper.toUsuarioEntity(usuarioDto);
        return mapper.toUsuarioDto(crudUsuario.save(usuario));
    }

    @Override
    public void delete(String id) {
        crudUsuario.deleteById(id);
    }

    @Override
    public Optional<UsuarioDto> getByCorreo(String correo) {
        return crudUsuario.findByCorreoElectronico(correo).map(usuario -> mapper.toUsuarioDto(usuario));
    }
}
