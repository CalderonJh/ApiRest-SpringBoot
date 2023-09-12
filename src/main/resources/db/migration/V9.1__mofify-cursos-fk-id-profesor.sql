alter table cursos
    modify id_profesor int not null,
    add constraint fk_prf
        foreign key (id_profesor)
            references universidad_api.profesores(id_profesor)