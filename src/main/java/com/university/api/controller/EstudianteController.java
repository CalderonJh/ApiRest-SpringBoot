package com.university.api.controller;

import com.university.api.domain.estudiante.*;
import com.university.api.domain.estudiante.dto.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    private final EstudianteRepository estudianteRepository;
    private final Services services;

    @Autowired
    public EstudianteController(EstudianteRepository estudianteRepository,
                                Services services) {
        this.estudianteRepository = estudianteRepository;
        this.services = services;
    }


    @PostMapping
    public ResponseEntity<EstudianteResponse> registrarEstudiante(@RequestBody @Valid Estudiante e,
                                                                  UriComponentsBuilder uriComponentsBuilder) {
        // asigna un código de estudiante siguiendo los requerimientos dados.
        e.setCodigo(services.generateNewCode());

        // si los campos de segundo nombre o segundo apellido son vacíos se asignará como nulos en la base de datos.
        e.verify();

        // registra el estudiante en la base de datos.
        var estudiante = estudianteRepository.save(e);

        URI uri = uriComponentsBuilder.path("/estudiante/{id}").buildAndExpand(estudiante.getId()).toUri();
        return ResponseEntity.created(uri).body(new EstudianteResponse(estudiante));
    }


    @GetMapping
    public ResponseEntity<Page<EstudianteGet>> listar(@PageableDefault(size = 12) Pageable pageable) {
        return ResponseEntity.ok(estudianteRepository.findByActivoTrue(pageable).map(EstudianteGet::new));
    }


    @GetMapping("/{id}")
    public ResponseEntity<EstudianteGet> getEstudiante(@PathVariable("id") Long id){
        var estudiante = estudianteRepository.getReferenceById(id);
        return ResponseEntity.ok(new EstudianteGet(estudiante));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EstudianteResponse> actualizarEstudiante(@PathVariable Long id,
                                                                   @RequestBody
                                                                   @Valid EstudiantePut estudiantePUT) {
        var estudiante = estudianteRepository.getReferenceById(id);
        estudiante.actualizar(estudiantePUT);
        return ResponseEntity.ok(new EstudianteResponse(estudiante));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarEstudiante(@PathVariable Long id) {
//      estudianteRepository.delete(estudianteRepository.getReferenceById(id));
        estudianteRepository.getReferenceById(id).desactivar();
        return ResponseEntity.ok("Un estudiante fue eliminado del registro.");
    }


}
