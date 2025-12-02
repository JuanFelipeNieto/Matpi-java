package com.matpi.dominio.servicios;

import com.matpi.dominio.dto.UsuarioDto;
import com.matpi.dominio.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioDto> getAll() {
        return usuarioRepositorio.getAll();
    }

    public Optional<UsuarioDto> getUsuario(String id) {
        return usuarioRepositorio.getUsuario(id);
    }

    public UsuarioDto save(UsuarioDto usuarioDto) {
        // Si viene password (formulario), copiarlo a contrasena (BD) y encriptar
        if (usuarioDto.getPassword() != null && !usuarioDto.getPassword().isEmpty()) {
            usuarioDto.setContrasena(passwordEncoder.encode(usuarioDto.getPassword()));
        } else if (usuarioDto.getContrasena() != null && !usuarioDto.getContrasena().isEmpty()) {
            // Si ya viene contrasena directamente, encriptarla
            usuarioDto.setContrasena(passwordEncoder.encode(usuarioDto.getContrasena()));
        }
        return usuarioRepositorio.save(usuarioDto);
    }

    public boolean delete(String id) {
        return getUsuario(id).map(usuario -> {
            usuarioRepositorio.delete(id);
            return true;
        }).orElse(false);
    }
}
