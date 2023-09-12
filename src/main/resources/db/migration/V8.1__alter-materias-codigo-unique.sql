-- corregir dato codigo debe ser único
alter table materias modify codigo int not null unique;