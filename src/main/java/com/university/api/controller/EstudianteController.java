package com.university.api.controller;

import com.university.api.domain.estudiante.*;
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
@RequestMapping("/estudiante")
public class EstudianteController {
    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteController(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }


    @PostMapping
    public ResponseEntity<EstudianteResponse> registrarEstudiante(@RequestBody @Valid Estudiante e,
                                                                  UriComponentsBuilder uriComponentsBuilder) {
        var estudiante = estudianteRepository.save(e);
        URI uri = uriComponentsBuilder.path("/estudiante/{id}").buildAndExpand(estudiante.getId()).toUri();
        return ResponseEntity.created(uri).body(new EstudianteResponse(estudiante));
    }


    @GetMapping
    public ResponseEntity<Page<EstudianteGET>> listar(@PageableDefault(size = 12) Pageable pageable) {
        return ResponseEntity.ok(estudianteRepository.findByActivoTrue(pageable).map(EstudianteGET::new));
    }


    @GetMapping("/{id}")
    public ResponseEntity<EstudianteResponse> getEstudiante(@PathVariable("id") Long id){
        var estudiante = estudianteRepository.getReferenceById(id);
        return ResponseEntity.ok(new EstudianteResponse(estudiante));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EstudianteResponse> actualizarEstudiante(@PathVariable Long id,
                                                       @RequestBody @Valid EstudiantePUT estudiantePUT) {
        var estudiante = estudianteRepository.getReferenceById(id);
        estudiante.actualizar(estudiantePUT);
        return ResponseEntity.ok(new EstudianteResponse(estudiante));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarEstudiante(@PathVariable Long id) {
//      estudianteRepository.delete(estudianteRepository.getReferenceById(id));
        estudianteRepository.getReferenceById(id).desactivar();
        return ResponseEntity.ok("Student removed :c");
    }


}
