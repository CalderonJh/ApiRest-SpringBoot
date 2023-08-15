package com.example.api.controller;

import com.example.api.profesor.DatosRegistroProfesor;
import com.example.api.profesor.ProfesorDTO;
import com.example.api.profesor.ProfesorRepository;
import com.example.api.profesor.Profesor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok("Successful professor creation c:");
    }
    @GetMapping
    public Page<ProfesorDTO> listar(@PageableDefault(size = 2) Pageable pageable){
        return profesorRepository.findAll(pageable).map(ProfesorDTO::new);
    }

    public void sum(int i){

    }

}
