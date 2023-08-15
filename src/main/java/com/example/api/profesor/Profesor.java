package com.example.api.profesor;

import com.example.api.direccion.Direccion;
import com.example.api.escuela.Escuela;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


@Entity(name = "Profesor")
@Table(name = "profesores")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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


}
