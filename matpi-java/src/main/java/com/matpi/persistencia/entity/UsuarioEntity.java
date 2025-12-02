package com.matpi.persistencia.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
public class UsuarioEntity implements UserDetails {

    @Id
    @Column(name = "id", length = 16)
    private String id;

    @Column(name = "telefono", length = 14, nullable = false)
    private String telefono;

    @Column(name = "contrasena", length = 255, nullable = false)
    private String contrasena;

    @Column(name = "correo_electronico", length = 35, nullable = false, unique = true)
    private String correoElectronico;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Rol rol;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "nombre_completo", length = 40, nullable = false)
    private String nombreCompleto;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "direccion", length = 50, nullable = false)
    private String direccion;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    @Column(name = "experiencia_laboral", length = 15, nullable = false)
    private String experienciaLaboral;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private AdministradorEntity administrador;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private EmpleadoEntity empleado;

    // Cliente no tiene relación directa OneToOne en el modelo original, se accede a
    // través de Empleado o consultas
    // Pero si se desea bidireccionalidad desde Usuario (si el usuario es un
    // cliente, aunque el modelo separa Cliente de Usuario)
    // Dejaremos esto comentado por ahora ya que ClienteEntity tiene relación con
    // EmpleadoEntity, no UsuarioEntity directamente en este diseño.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public String getPassword() {
        return this.contrasena;
    }

    @Override
    public String getUsername() {
        return this.id; // Usamos el ID como username según el modelo original
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.estado;
    }
}
