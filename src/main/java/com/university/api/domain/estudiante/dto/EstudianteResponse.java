package com.university.api.domain.estudiante.dto;

import com.university.api.Direccion.Direccion;
import com.university.api.domain.estudiante.Estudiante;

import java.math.BigInteger;

public record EstudianteResponse(String primerNombre,
                                 String segundoNombre,
                                 String primerApellido,
                                 String segundoApellido,
                                 String email,
                                 BigInteger documento,
                                 String escuela,
                                 String programaAcademico,
                                 BigInteger codigo,
                                 String telefono,
                                 Direccion direccion,
                                 Boolean activo){

    public EstudianteResponse(Estudiante e){
        this(
                e.getPrimerNombre(),
                e.getSegundoNombre(),
                e.getPrimerApellido(),
                e.getSegundoApellido(),
                e.getEmail(),
                e.getDocumento(),
                e.getEscuela().toString(),
                e.getProgramaAcademico().toString(),
                e.getCodigo(),
                e.getTelefono(),
                e.getDireccion(),
                e.getActivo()
        );
    }
}
