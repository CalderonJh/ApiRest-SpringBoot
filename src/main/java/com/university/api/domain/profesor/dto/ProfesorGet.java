package com.university.api.domain.profesor.dto;

import com.university.api.domain.profesor.Profesor;

public record ProfesorGet(String primerNombre,
                          String segundoNombre,
                          String primerApellido,
                          String segundoApellido,
                          String escuela,
                          String email){

    public ProfesorGet(Profesor profesor){
        this(
                profesor.getPrimerNombre(),
                profesor.getSegundoNombre(),
                profesor.getPrimerApellido(),
                profesor.getSegundoApellido(),
                profesor.getEscuela().toString(),
                profesor.getEmail()
        );
    }

}
