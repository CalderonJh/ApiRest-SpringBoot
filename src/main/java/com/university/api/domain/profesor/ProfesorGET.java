package com.university.api.domain.profesor;

public record ProfesorGET(Long id, String primerNombre, String segundoNombre,
                          String primerApellido, String segundoApellido,
                          String escuela, String email){

    public ProfesorGET(Profesor profesor){

        this(profesor.getId(), profesor.getPrimerNombre(), profesor.getSegundoNombre(),
                profesor.getPrimerApellido(), profesor.getSegundoApellido(),
                profesor.getEscuela().toString(), profesor.getEmail());
    }

}
