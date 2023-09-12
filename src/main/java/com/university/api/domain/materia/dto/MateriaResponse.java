package com.university.api.domain.materia.dto;

import com.university.api.domain.materia.Materia;

public record MateriaResponse(Long id, int codigo, String nombre, String escuela) {
    public MateriaResponse(Materia m){
        this(m.getId(), m.getCodigo(), m.getNombre(), m.getEscuela().toString());
    }
}
