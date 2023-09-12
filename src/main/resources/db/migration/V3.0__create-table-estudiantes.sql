create table estudiantes
(
    id_estudiante      int auto_increment not null,
    primer_nombre      varchar(50)        not null,
    segundo_nombre     varchar(50),
    primer_apellido    varchar(50)        not null,
    segundo_apellido   varchar(50),
    email              varchar(50)        not null unique,
    documento          bigint             not null unique,
    codigo             bigint             not null unique,
    facultad           varchar(100)        not null,
    escuela            varchar(100)        not null,
    programa_academico varchar(100)        not null,
    telefono           varchar(100),
    id_direccion       int,
    activo             bool default true,
    primary key (id_estudiante),
    foreign key (id_direccion) references universidad_api.direcciones (id_direccion)
)