package com.university.api.domain.estudiante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;

@Service
public class Services {
    private final EstudianteRepository estudianteRepository;

    @Autowired
    public Services(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public BigInteger generateNewCode(){
        return latestCode().add(BigInteger.valueOf(1));
    }

    /**
     * Encuentra el codigo asignado al último registro en la base de datos.
     * @return ultimo codigo asignado a un prefijo determinado.
     */
    private BigInteger latestCode(){
        var code = estudianteRepository.findLatestCodeByPrefix(generatePrefix());
        if (code == null)
            code = BigInteger.valueOf(Long.parseLong(generatePrefix() + "000"));

        return code;
    }


    /**
     * Obtener el prefijo o tres primeros dígitos para el codigo de estudiante
     * @return el prefijo adecuado según la fecha en la que se realice el registro
     */
    private String generatePrefix() {
        var date = LocalDate.now();
        var year = date.getYear() % 100;
        int month = date.getMonthValue();
        if (month >= 8 || month <= 4) {
            month = 1;
        } else {
            month = 2;
        }
        return String.valueOf(year).concat(String.valueOf(month));
    }
}
