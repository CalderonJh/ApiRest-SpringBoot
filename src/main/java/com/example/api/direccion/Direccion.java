package com.example.api.direccion;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity(name = "Direccion")
@Table(name = "direcciones")
public class Direccion {
    @Id
    @Column(name = "id_direccion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String carrera;

    @NotBlank
    private String calle;

    private String barrio;

    @NotBlank
    private String ciudad;

    private String complemento;

}
