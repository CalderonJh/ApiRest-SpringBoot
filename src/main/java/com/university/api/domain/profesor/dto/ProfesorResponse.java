package com.university.api.domain.profesor.dto;


import com.university.api.Direccion.Direccion;
import com.university.api.domain.profesor.Profesor;

import java.math.BigInteger;

public record ProfesorResponse(Long id, String primerNombre, String segundoNombre, String primerApellido,
                               String segundoApellido, String email, BigInteger documento, String escuela,
                               String telefono, Direccion direccion, Boolean activo) {
    public ProfesorResponse(Profesor profesor){
        this(profesor.getId(), profesor.getPrimerNombre(),  profesor.getSegundoNombre(),profesor.getPrimerApellido(),
                profesor.getSegundoApellido(), profesor.getEmail(),profesor.getDocumento(),
                profesor.getEscuela().toString(), profesor.getTelefono(),
                profesor.getDireccion(), profesor.getActivo());
    }
}
