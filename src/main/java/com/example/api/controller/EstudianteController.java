package com.example.api.controller;

import com.example.api.estudiante.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteController(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @PostMapping
    public ResponseEntity<String> registrarEstudiante(@RequestBody @Valid Estudiante estudiante) {
        estudianteRepository.save(estudiante);
        return ResponseEntity.ok("Successful student registration c:");
    }

    @GetMapping
    public Page<EstudianteDTO> listar(@PageableDefault(size = 2) Pageable pageable) {
        return estudianteRepository.findByActivoTrue(pageable).map(EstudianteDTO::new);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> actualizarEstudiante(@PathVariable Long id,
                                                       @RequestBody @Valid EstudiantePUT estudiantePUT) {
        estudianteRepository.getReferenceById(id).actualizar(estudiantePUT);
        return ResponseEntity.ok("Student data updated c:");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarEstudiante(@PathVariable Long id) {
//      estudianteRepository.delete(estudianteRepository.getReferenceById(id));
        estudianteRepository.getReferenceById(id).desactivar();
        return ResponseEntity.ok("Student removed :c");
    }


}
