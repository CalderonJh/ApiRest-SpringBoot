create table inscripciones(
    id_inscripcion bigint auto_increment,
    id_curso int,
    id_estudiante int,
    primary key (id_inscripcion),
    foreign key (id_curso) references universidad_api.cursos(id_curso),
    foreign key (id_estudiante) references universidad_api.estudiantes(id_estudiante)
)