create table cursos(
    id_curso int auto_increment,
    materia varchar(50) not null,
    id_profesor int,
    primary key (id_curso)
)