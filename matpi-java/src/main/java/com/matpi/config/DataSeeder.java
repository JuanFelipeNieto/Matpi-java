package com.matpi.config;

import com.matpi.persistencia.crud.CrudUsuario;
import com.matpi.persistencia.entity.Rol;
import com.matpi.persistencia.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private CrudUsuario crudUsuario;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verificar si existe el admin
        if (crudUsuario.existsById("1011202777")) {
            UsuarioEntity admin = crudUsuario.findById("1011202777").get();
            admin.setContrasena(passwordEncoder.encode("admin123"));
            crudUsuario.save(admin);
            System.out.println("Contraseña de Administrador (1011202777) actualizada a: admin123");
        } else {
            UsuarioEntity admin = new UsuarioEntity();
            admin.setId("1011202777"); // ID del administrador
            admin.setNombreCompleto("Administrador Principal");
            admin.setCorreoElectronico("admin@matpi.com");
            admin.setTelefono("3001234567");
            admin.setContrasena(passwordEncoder.encode("admin123"));
            admin.setRol(Rol.ADMINISTRADOR);
            admin.setFechaIngreso(LocalDate.now());
            admin.setEstado(true);
            admin.setDireccion("Calle Falsa 123");
            admin.setExperienciaLaboral("Sin experiencia");

            crudUsuario.save(admin);
            System.out.println("Usuario Administrador creado: admin@matpi.com / admin123");
        }

        // Crear empleado de prueba si no existe
        if (!crudUsuario.existsById("1002")) {
            UsuarioEntity empleado = new UsuarioEntity();
            empleado.setId("1002");
            empleado.setNombreCompleto("Empleado de Prueba");
            empleado.setCorreoElectronico("empleado@matpi.com");
            empleado.setTelefono("3007654321");
            empleado.setContrasena(passwordEncoder.encode("empleado123"));
            empleado.setRol(Rol.EMPLEADO);
            empleado.setFechaIngreso(LocalDate.now());
            empleado.setEstado(true);
            empleado.setDireccion("Avenida Siempre Viva 742");
            empleado.setExperienciaLaboral("1 año");

            crudUsuario.save(empleado);
            System.out.println("Usuario Empleado creado: empleado@matpi.com / empleado123");
        }
    }
}
