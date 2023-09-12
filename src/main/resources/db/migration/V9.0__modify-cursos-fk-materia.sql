alter table cursos
    change materia id_materia int not null,
    add constraint fk_cd
        foreign key (id_materia)
            references universidad_api.materias (id_materia)