create table materias(
    id_materia int auto_increment,
    nombre varchar(50) not null,
    codigo int not null,
    escuela varchar(100),
    primary key (id_materia)
)