package com.matpi.dominio.servicios;

import com.matpi.persistencia.crud.CrudUsuario;
import com.matpi.persistencia.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private CrudUsuario crudUsuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // En este caso, el username es el ID del usuario
        System.out.println("Intento de login para usuario: " + username);
        UsuarioEntity usuario = crudUsuario.findById(username)
                .orElseThrow(() -> {
                    System.out.println("Usuario NO encontrado: " + username);
                    return new UsernameNotFoundException("Usuario no encontrado con ID: " + username);
                });

        System.out.println("Usuario encontrado: " + usuario.getNombreCompleto());
        System.out.println("Hash de contraseña en DB: " + usuario.getPassword());
        System.out.println("Rol: " + usuario.getRol());
        System.out.println("Estado: " + usuario.getEstado());

        return usuario;
    }
}
