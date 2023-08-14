package com.example.api.controller;

import com.example.api.profesor.ProfesorRepository;
import com.example.api.profesor.Profesor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Profesor> listar(){
        return profesorRepository.findAll();
    }

}
