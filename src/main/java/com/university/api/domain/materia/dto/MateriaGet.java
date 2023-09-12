package com.university.api.domain.materia.dto;

import com.university.api.domain.materia.Materia;

public record MateriaGet(int codigo, String nombre, String escuela) {
    public MateriaGet(Materia materia) {
        this(materia.getCodigo(), materia.getNombre(), materia.getEscuela().toString());
    }
}
