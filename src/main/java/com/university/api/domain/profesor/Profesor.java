package com.university.api.domain.profesor;

import com.university.api.direccion.Direccion;
import com.university.api.escuela.Escuela;
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


@Entity(name = "Profesor")
@Table(name = "profesores")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
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

    @NotBlank
    private String telefono;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_direccion")
    @Valid
    private Direccion direccion;

    @Column(name = "activo")
    private Boolean activo = true;

    public void actualizar(ProfesorPUT profesorPUT) {
        if (profesorPUT.primerNombre() != null)
            this.primerNombre = profesorPUT.primerNombre();
        if (profesorPUT.segundoNombre() != null)
            this.segundoNombre = profesorPUT.segundoNombre();
        if (profesorPUT.primerApellido() != null)
            this.primerApellido = profesorPUT.primerApellido();
        if (profesorPUT.segundoApellido() != null)
            this.segundoApellido = profesorPUT.segundoApellido();
        if (profesorPUT.activo() != null)
            this.activo = profesorPUT.activo();

    }

    public void desactivarProfesor() {
        this.activo = false;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o)
                .getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Profesor profesor = (Profesor) o;
        return getId() != null && Objects.equals(getId(), profesor.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
