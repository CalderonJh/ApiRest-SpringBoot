package com.example.api.controller;

import com.example.api.profesor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    private final ProfesorRepository profesorRepository;

    @Autowired
    public ProfesorController(ProfesorRepository p){
        this.profesorRepository = p;
    }

    @PostMapping
    public ResponseEntity<String> registrarProfesor(@RequestBody @Valid Profesor profesor){
        profesorRepository.save(profesor);
        return ResponseEntity.ok("Successful professor registration c:");
    }

    @GetMapping
    public Page<ProfesorDTO> listar(@PageableDefault Pageable pageable){
        return profesorRepository.findByActivoTrue(pageable).map(ProfesorDTO::new);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> actualizarProfesor(@PathVariable("id") Long id,
                                                     @RequestBody ProfesorPUT profesorPUT) {
        Profesor profesor = profesorRepository.getReferenceById(id);
        profesor.actualizar(profesorPUT);

        return ResponseEntity.ok("Professor data updated c:");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarProfesor(@PathVariable Long id){
        Profesor profesor = profesorRepository.getReferenceById(id);
        profesor.desactivarProfesor();
        return ResponseEntity.ok("Professor removed :c");
    }

}
