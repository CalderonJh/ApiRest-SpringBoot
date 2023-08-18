package com.example.api.profesor;

public record ProfesorDTO(Long id, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String escuela, String email){

    public ProfesorDTO(Profesor profesor){

        this(profesor.getId(), profesor.getPrimerNombre(), profesor.getSegundoNombre(), profesor.getPrimerApellido(), profesor.getSegundoApellido(), profesor.getEscuela().toString(), profesor.getEmail());
    }

}
