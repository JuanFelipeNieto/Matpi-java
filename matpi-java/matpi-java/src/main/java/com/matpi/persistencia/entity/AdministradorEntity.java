package com.matpi.persistencia.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "administrador")
@Data
@NoArgsConstructor
public class AdministradorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID interno para JPA, aunque la relación es por ID_Usr

    @Column(name = "ult_fecha_login")
    private LocalDateTime ultFechaLogin;

    @Column(name = "ult_ip_login", length = 45)
    private String ultIpLogin;

    @Column(name = "formacion_educativa", length = 35)
    private String formacionEducativa;

    @OneToOne
    @JoinColumn(name = "id_usr", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuario;
}
