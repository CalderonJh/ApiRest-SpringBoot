package com.example.api.direccion;

public record DatosRegistroDireccion(
        String carrera, String calle,
        String barrio, String ciudad,
        String complemento) {
}
