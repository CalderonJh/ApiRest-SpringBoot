create table estudiantes
(
    id_estudiante      int auto_increment not null,
    primer_nombre      varchar(20)        not null,
    segundo_nombre     varchar(20),
    primer_apellido    varchar(20)        not null,
    segundo_apellido   varchar(20),
    email              varchar(25)        not null unique,
    documento          bigint             not null unique,
    Facultad           varchar(25),
    programa_academico varchar(25)        not null,
    telefono           varchar(15),
    id_direccion       int,
    primary key (id_estudiante),
    foreign key (id_direccion) references universidad_api.direcciones (id_direccion)
)