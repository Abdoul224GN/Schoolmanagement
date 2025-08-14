package org.school.controller;

import lombok.AllArgsConstructor;
import org.school.dto.ClasseRequestDTO;
import org.school.dto.ClasseResponseDTO;
import org.school.service.ClasseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/classe")
@AllArgsConstructor
public class ClasseController {
    ClasseService classeService;

    @GetMapping
    public ResponseEntity<List<ClasseResponseDTO>> getAllClasses() {
        return ResponseEntity.status(HttpStatus.OK).body(classeService.getAllClasses());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ClasseResponseDTO> getClasseById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(classeService.getClassById(id));
    }

    @PostMapping
    public ResponseEntity<ClasseResponseDTO> createClasse(@RequestBody ClasseRequestDTO classeRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(classeService.createClasse(classeRequestDTO));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ClasseResponseDTO> updateClasse(@PathVariable Long id, @RequestBody ClasseRequestDTO classeRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(classeService.updateClasse(id, classeRequestDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteClasse(@PathVariable Long id) {
        classeService.deleteClasse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
