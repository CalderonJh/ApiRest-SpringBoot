package com.university.api.domain.profesor.dto;

public record ProfesorPut(String primerNombre,
                          String segundoNombre,
                          String primerApellido,
                          String segundoApellido,
                          Boolean activo) {
}
