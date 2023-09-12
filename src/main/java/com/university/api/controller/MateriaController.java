package com.university.api.controller;

import com.university.api.domain.materia.Materia;
import com.university.api.domain.materia.MateriaRepository;
import com.university.api.domain.materia.dto.MateriaGet;
import com.university.api.domain.materia.dto.MateriaPut;
import com.university.api.domain.materia.dto.MateriaResponse;
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
@RequestMapping("/materia")
public class MateriaController {

    private final MateriaRepository materiaRepository;

    @Autowired
    public MateriaController(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }


    @PostMapping
    public ResponseEntity<MateriaResponse> crearMateria(@RequestBody @Valid Materia m,
                                                        UriComponentsBuilder uriComponentsBuilder){
        var materia = materiaRepository.save(m);
        URI uri = uriComponentsBuilder.path("/crear-materia/{id}").buildAndExpand(materia.getId()).toUri();
        return ResponseEntity.created(uri).body(new MateriaResponse(materia));
    }


    @GetMapping
    public ResponseEntity<Page<MateriaGet>> get(@PageableDefault(size = 12)Pageable pageable){
        return ResponseEntity.ok(materiaRepository.findAll(pageable).map(MateriaGet::new));
    }


    @GetMapping("/{id}")
    public ResponseEntity<MateriaGet> getMateria(@PathVariable(name = "id") Long id){
        var materia = materiaRepository.getReferenceById(id);
        return ResponseEntity.ok(new MateriaGet(materia));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MateriaResponse> edit(@PathVariable(name = "id") Long id,
                                                MateriaPut materiaPut){
        var materia = materiaRepository.getReferenceById(id);
        materia.actualizar(materiaPut);
        return ResponseEntity.ok(new MateriaResponse(materia));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarMateria(@PathVariable(name = "id") Long id){
        materiaRepository.delete(materiaRepository.getReferenceById(id));
        return ResponseEntity.ok("Materia eliminada con éxito");
    }

}
