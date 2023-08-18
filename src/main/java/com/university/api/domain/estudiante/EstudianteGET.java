package com.university.api.domain.estudiante;

public record EstudianteGET(Long id,
                            String primerNombre,
                            String segundoNombre,
                            String primerApellido,
                            String segundoApellido,
                            String escuela,
                            String programaAcademico,
                            String email) {

    public EstudianteGET(Estudiante estudiante) {
        this(estudiante.getId(),
                estudiante.getPrimerNombre(),
                estudiante.getSegundoNombre(),
                estudiante.getPrimerApellido(),
                estudiante.getSegundoApellido(),
                estudiante.getEscuela().toString(),
                estudiante.getProgramaAcademico().toString(),
                estudiante.getEmail());
    }
}
