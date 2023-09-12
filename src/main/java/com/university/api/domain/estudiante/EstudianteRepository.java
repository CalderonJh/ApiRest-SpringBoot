package com.university.api.domain.estudiante;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface EstudianteRepository
        extends JpaRepository<Estudiante, Long> {

    Page<Estudiante> findByActivoTrue(Pageable pageable);

    @Query("SELECT e.codigo " +
            "FROM Estudiante e " +
            "WHERE CAST(e.codigo AS string) LIKE CONCAT(:prefix, '%') " +  // Corrección aquí
            "ORDER BY e.codigo DESC " +
            "LIMIT 1")
    BigInteger findLatestCodeByPrefix(@Param("prefix") String prefix) throws TypeNotPresentException;

}
