create table direcciones
(
    id_direccion int auto_increment not null,
    carrera      varchar(25) not null,
    calle        varchar(25) not null,
    barrio       varchar(25),
    ciudad       varchar(25) not null,
    complemento  varchar(25),
    primary key (id_direccion)
)