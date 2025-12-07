package com.matpi.dominio.servicios;

import com.matpi.dominio.dto.EmpleadoDto;
import com.matpi.dominio.dto.UsuarioDto;
import com.matpi.dominio.repositorio.EmpleadoRepositorio;
import com.matpi.persistencia.crud.CrudEmpleado;
import com.matpi.persistencia.entity.EmpleadoEntity;
import com.matpi.persistencia.entity.UsuarioEntity;
import com.matpi.persistencia.mapper.EmpleadoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CrudEmpleado crudEmpleado;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmpleadoMapper empleadoMapper;

    public List<EmpleadoDto> getAll() {
        return empleadoRepositorio.getAll();
    }

    public Optional<EmpleadoDto> getEmpleado(String id) {
        return empleadoRepositorio.getEmpleado(id);
    }

    @Transactional
    public EmpleadoDto save(EmpleadoDto empleadoDto) {
        // 1. Crear y guardar el Usuario primero
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(empleadoDto.getId());
        usuarioDto.setTelefono(empleadoDto.getTelefono());
        usuarioDto.setPassword(empleadoDto.getPassword());
        usuarioDto.setCorreoElectronico(empleadoDto.getCorreoElectronico());
        usuarioDto.setNombreCompleto(empleadoDto.getNombreCompleto());
        usuarioDto.setDireccion(empleadoDto.getDireccion());
        usuarioDto.setExperienciaLaboral(empleadoDto.getExperienciaLaboral());
        usuarioDto.setFechaNacimiento(empleadoDto.getFechaNacimiento());
        usuarioDto.setFechaIngreso(empleadoDto.getFechaIngreso());
        usuarioDto.setEstado(empleadoDto.getEstado() != null ? empleadoDto.getEstado() : true);
        usuarioDto.setRol(empleadoDto.getRol() != null ? empleadoDto.getRol() : "EMPLEADO");

        // Guardar el usuario - esto hace flush y commit
        UsuarioDto usuarioGuardado = usuarioService.save(usuarioDto);

        // 2. Ahora crear el Empleado con el ID del usuario guardado
        EmpleadoDto empleadoDtoParaGuardar = new EmpleadoDto();
        empleadoDtoParaGuardar.setIdUsr(usuarioGuardado.getId());
        empleadoDtoParaGuardar.setEps(empleadoDto.getEps());
        empleadoDtoParaGuardar.setTipoContrato(empleadoDto.getTipoContrato());
        empleadoDtoParaGuardar.setContactoEmergenciaNombre(empleadoDto.getContactoEmergenciaNombre());
        empleadoDtoParaGuardar.setContactoEmergenciaParentesco(empleadoDto.getContactoEmergenciaParentesco());
        empleadoDtoParaGuardar.setContactoEmergenciaNumero(empleadoDto.getContactoEmergenciaNumero());
        empleadoDtoParaGuardar.setFechaTerminacionContrato(empleadoDto.getFechaTerminacionContrato());

        return empleadoRepositorio.save(empleadoDtoParaGuardar);
    }

    @Transactional
    public EmpleadoDto update(EmpleadoDto empleadoDto) {
        String id = empleadoDto.getId();

        // Fetch the existing entity (this loads it into the session)
        return crudEmpleado.findById(id)
                .map(empleadoEntity -> {
                    // Update Usuario fields on the existing entity
                    UsuarioEntity usuario = empleadoEntity.getUsuario();
                    if (usuario != null) {
                        usuario.setTelefono(empleadoDto.getTelefono());
                        usuario.setCorreoElectronico(empleadoDto.getCorreoElectronico());
                        usuario.setNombreCompleto(empleadoDto.getNombreCompleto());
                        usuario.setDireccion(empleadoDto.getDireccion());
                        usuario.setExperienciaLaboral(empleadoDto.getExperienciaLaboral());
                        usuario.setFechaNacimiento(empleadoDto.getFechaNacimiento());
                        usuario.setEstado(empleadoDto.getEstado());

                        // Update password if provided
                        if (empleadoDto.getPassword() != null && !empleadoDto.getPassword().isEmpty()) {
                            usuario.setContrasena(passwordEncoder.encode(empleadoDto.getPassword()));
                        }
                    }

                    // Update Empleado fields on the existing entity
                    empleadoEntity.setEps(empleadoDto.getEps());
                    empleadoEntity.setTipoContrato(empleadoDto.getTipoContrato());
                    empleadoEntity.setContactoEmergenciaNombre(empleadoDto.getContactoEmergenciaNombre());
                    empleadoEntity.setContactoEmergenciaParentesco(empleadoDto.getContactoEmergenciaParentesco());
                    empleadoEntity.setContactoEmergenciaNumero(empleadoDto.getContactoEmergenciaNumero());
                    empleadoEntity.setFechaTerminacionContrato(empleadoDto.getFechaTerminacionContrato());

                    // Save (JPA handles persistence)
                    EmpleadoEntity saved = crudEmpleado.save(empleadoEntity);
                    return empleadoMapper.toEmpleadoDto(saved);
                })
                .orElse(null);
    }

    @Transactional
    public boolean delete(String id) {
        // Al eliminar el usuario, se elimina el empleado por cascada (configurado en
        // UsuarioEntity)
        return usuarioService.delete(id);
    }
}
