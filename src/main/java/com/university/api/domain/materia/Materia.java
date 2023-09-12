package com.university.api.domain.materia;

import com.university.api.domain.materia.dto.MateriaPut;
import com.university.api.facultad.Escuela;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@Entity(name = "Materia")
@Table(name = "materias")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Long id;

    @NotNull
    private int codigo;

    @NotNull
    private String nombre;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Escuela escuela;


    public void actualizar(MateriaPut materiaPut) {
        if(materiaPut.nombre() != null){
            this.nombre = materiaPut.nombre();
        }if(materiaPut.codigo() != null){
            this.codigo = materiaPut.codigo();
        }if(materiaPut.escuela() != null){
            this.escuela= materiaPut.escuela();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materia materia = (Materia) o;
        return Objects.equals(id, materia.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
