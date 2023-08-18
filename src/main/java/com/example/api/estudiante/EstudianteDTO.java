package com.example.api.estudiante;

public record EstudianteDTO(Long id, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
                            String escuela, String programaAcademico, String email) {

    public EstudianteDTO(Estudiante estudiante) {

        this(estudiante.getId(), estudiante.getPrimerNombre(), estudiante.getSegundoNombre(), estudiante.getPrimerApellido(),
                estudiante.getSegundoApellido(), estudiante.getEscuela().toString(),
                estudiante.getProgramaAcademico().toString(), estudiante.getEmail());
    }

}
