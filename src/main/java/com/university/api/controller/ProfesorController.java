package com.university.api.controller;

import com.university.api.domain.profesor.Profesor;
import com.university.api.domain.profesor.ProfesorRepository;
import com.university.api.domain.profesor.dto.ProfesorGet;
import com.university.api.domain.profesor.dto.ProfesorPut;
import com.university.api.domain.profesor.dto.ProfesorResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    private final ProfesorRepository profesorRepository;

    @Autowired
    public ProfesorController(ProfesorRepository p){
        this.profesorRepository = p;
    }


    @PostMapping
    public ResponseEntity<ProfesorResponse> registrarProfesor(@RequestBody @Valid Profesor p,
                                                              UriComponentsBuilder uriComponentsBuilder){
        var profesor = profesorRepository.save(p);

        URI url = uriComponentsBuilder.path("/profesor/{id}").buildAndExpand(profesor.getId()).toUri();
        return ResponseEntity.created(url).body(new ProfesorResponse(profesor));
    }


    @GetMapping
    public ResponseEntity<Page<ProfesorGet>> listar(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(profesorRepository.findByActivoTrue(pageable).map(ProfesorGet::new));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProfesorGet> getProfesor(@PathVariable("id") Long id){
        var profesor = profesorRepository.getReferenceById(id);
        return ResponseEntity.ok(new ProfesorGet(profesor));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProfesorResponse> actualizarProfesor(@PathVariable("id") Long id,
                                                               @RequestBody ProfesorPut profesorPUT) {
        Profesor profesor = profesorRepository.getReferenceById(id);
        profesor.actualizar(profesorPUT);
        profesor.actualizar(profesorPUT);

        return ResponseEntity.ok(new ProfesorResponse(profesor));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarProfesor(@PathVariable Long id){
        profesorRepository.getReferenceById(id).desactivarProfesor();
        return ResponseEntity.noContent().build();
    }

}
