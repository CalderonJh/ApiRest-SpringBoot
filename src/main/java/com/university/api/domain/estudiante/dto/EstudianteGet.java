package com.university.api.domain.estudiante.dto;

import com.university.api.domain.estudiante.Estudiante;

public record EstudianteGet(String primerNombre,
                            String segundoNombre,
                            String primerApellido,
                            String segundoApellido,
                            String escuela,
                            String programaAcademico,
                            String email) {

    public EstudianteGet(Estudiante estudiante) {
        this(
                estudiante.getPrimerNombre(),
                estudiante.getSegundoNombre(),
                estudiante.getPrimerApellido(),
                estudiante.getSegundoApellido(),
                estudiante.getEscuela().toString(),
                estudiante.getProgramaAcademico().toString(),
                estudiante.getEmail()
        );
    }
}
