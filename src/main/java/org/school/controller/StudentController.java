package org.school.controller;

import lombok.RequiredArgsConstructor;
import org.school.dto.PaginationResponseDTO;
import org.school.dto.StudentRequestDTO;
import org.school.dto.StudentResponseDTO;
import org.school.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // ✅ Récupérer tous les élèves
    @GetMapping
    public ResponseEntity<PaginationResponseDTO<StudentResponseDTO>> getAllStudents(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(studentService.getAllStudents(page, size, sortBy, direction));
    }

    // ✅ Récupérer un élève par ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // ✅ Créer un nouvel élève
    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO studentDTO) {
        return ResponseEntity.ok(studentService.createStudent(studentDTO));
    }

    // ✅ Mettre à jour un élève
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id, @RequestBody StudentRequestDTO studentDTO) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentDTO));
    }

    // ✅ Supprimer un élève
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "{id}/photo")
    ResponseEntity<StudentResponseDTO> uploadPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(studentService.uploadPhoto(id, file));
    }
}
