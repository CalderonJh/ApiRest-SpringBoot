package com.example.api.profesor;

import com.example.api.direccion.DatosRegistroDireccion;
import com.example.api.escuela.Escuela;

public record DatosRegistroProfesor(
        String nombre, String email, Integer cedula,
        Escuela escuela, DatosRegistroDireccion direccion) {
}
