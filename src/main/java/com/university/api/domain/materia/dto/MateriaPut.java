package com.university.api.domain.materia.dto;

import com.university.api.facultad.Escuela;

public record MateriaPut(Integer codigo,
                         String nombre,
                         Escuela escuela) {
}
