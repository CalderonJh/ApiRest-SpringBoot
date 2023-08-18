package com.example.api.estudiante;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository
        extends JpaRepository<Estudiante, Long> {

    Page<Estudiante> findByActivoTrue(Pageable pageable);
}
