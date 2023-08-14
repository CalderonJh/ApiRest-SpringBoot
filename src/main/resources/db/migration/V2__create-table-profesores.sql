create table profesores
(
    id_profesor      int auto_increment not null,
    primer_nombre    varchar(20) not null,
    segundo_nombre   varchar(20),
    primer_apellido  varchar(20) not null,
    segundo_apellido varchar(20),
    email            varchar(25) not null unique,
    documento        int         not null unique,
    escuela          varchar(25),
    telefono         varchar(15),
    id_direccion     int,
    primary key (id_profesor),
    foreign key (id_direccion) references universidad_api.direcciones (id_direccion)
)