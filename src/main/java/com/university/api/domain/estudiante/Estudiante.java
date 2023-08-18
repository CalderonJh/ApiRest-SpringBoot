package com.university.api.domain.estudiante;

import com.university.api.direccion.Direccion;
import com.university.api.escuela.Escuela;
import com.university.api.escuela.ProgramaAcademico;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigInteger;
import java.util.Objects;


@Entity(name = "Estudiante")
@Table(name = "estudiantes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Long id;

    @NotBlank
    @Column(name = "primer_nombre")
    private String primerNombre;

    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @NotBlank
    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private BigInteger documento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Escuela escuela;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "programa_academico")
    private ProgramaAcademico programaAcademico;

    @NotBlank
    private String telefono;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_direccion")
    @Valid
    private Direccion direccion;

    @Column(name = "activo")
    private boolean activo = true;


    public void actualizar(EstudiantePUT estudiantePUT) {
        if (estudiantePUT.primerNombre() != null)
            this.primerNombre = estudiantePUT.primerNombre();
        if (estudiantePUT.segundoNombre() != null)
            this.segundoNombre = estudiantePUT.segundoNombre();
        if (estudiantePUT.primerApellido() != null)
            this.primerApellido = estudiantePUT.primerApellido();
        if (estudiantePUT.segundoApellido() != null)
            this.segundoApellido = estudiantePUT.segundoApellido();
        if (estudiantePUT.activo() != null)
            this.activo = estudiantePUT.activo();
    }


    public void desactivar() {
        this.activo = false;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Estudiante that = (Estudiante) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass()
                .hashCode() : getClass().hashCode();
    }
}
