package com.matpi.persistencia.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "empleado")
@Data
@NoArgsConstructor
public class EmpleadoEntity implements Persistable<String> {

    @Id
    @Column(name = "id_usr", length = 16)
    private String idUsr;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usr")
    private UsuarioEntity usuario;

    @Column(name = "eps", nullable = false)
    private String eps;

    @Column(name = "tipo_contrato", nullable = false)
    private String tipoContrato;

    @Column(name = "contacto_emergencia_nombre", length = 35, nullable = false)
    private String contactoEmergenciaNombre;

    @Column(name = "contacto_emergencia_parentesco", length = 15, nullable = false)
    private String contactoEmergenciaParentesco;

    @Column(name = "contacto_emergencia_numero", length = 14, nullable = false)
    private String contactoEmergenciaNumero;

    @Column(name = "fecha_terminacion_contrato")
    private LocalDate fechaTerminacionContrato;

    @Transient
    private boolean isNew = true;

    @Override
    public String getId() {
        return idUsr;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PrePersist
    @PostLoad
    void markNotNew() {
        this.isNew = false;
    }
}
