package com.university.api.domain.profesor;


import com.university.api.direccion.Direccion;

import java.math.BigInteger;

public record ProfesorResponse(String primerNombre, String segundoNombre, String primerApellido,
                               String segundoApellido, String email, BigInteger documento, String escuela,
                               String telefono, Direccion direccion, Boolean activo) {
    public ProfesorResponse(Profesor profesor){
        this(profesor.getPrimerNombre(),  profesor.getSegundoNombre(),profesor.getPrimerApellido(),
                profesor.getSegundoApellido(), profesor.getEmail(),profesor.getDocumento(),
                profesor.getEscuela().toString(), profesor.getTelefono(),
                profesor.getDireccion(), profesor.getActivo());
    }
}
