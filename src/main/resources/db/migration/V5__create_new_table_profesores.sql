create table profesores2(
                           id_profesor int auto_increment,
                           nombre varchar(50),
                           apellido varchar(50),
                           email varchar(25),
                           cedula int,
                           escuela varchar(25),
                           telefono varchar(15),
                           id_direccion int,
                           primary key (id_profesor),
                           foreign key (id_direccion) references universidad_api.direcciones(id_direccion)
)