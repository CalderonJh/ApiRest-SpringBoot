create table profesores
(
    id_profesor      int auto_increment not null,
    primer_nombre    varchar(20) not null,
    segundo_nombre   varchar(20),
    primer_apellido  varchar(20) not null,
    segundo_apellido varchar(20),
    email            varchar(25) not null unique,
    documento        bigint         not null unique,
    facultad varchar(50) not null,
    escuela          varchar(50),
    telefono         varchar(25),
    id_direccion     int,
    activo           bool default true,
    primary key (id_profesor),
    foreign key (id_direccion) references universidad_api.direcciones (id_direccion)
)