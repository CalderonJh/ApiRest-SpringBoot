package com.example.api.profesor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository
        extends JpaRepository<Profesor, Long> {

    Page<Profesor> findByActivoTrue(Pageable pageable);
}
