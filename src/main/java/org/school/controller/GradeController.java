package org.school.controller;

import lombok.AllArgsConstructor;
import org.school.dto.GradeRequestDTO;
import org.school.dto.GradeResponseDTO;
import org.school.dto.PaginationResponseDTO;
import org.school.service.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/grade")
public class GradeController {

    GradeService gradeService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaginationResponseDTO<GradeResponseDTO>> getAllGrades(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(gradeService.getAllGrade(page, size, sortBy, direction));
    }

    @GetMapping(path = "/search")
    public ResponseEntity<PaginationResponseDTO<GradeResponseDTO>> searchByName(@RequestParam String keyWord) {
        return ResponseEntity.status(HttpStatus.OK).body(gradeService.searchByName(keyWord));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<GradeResponseDTO> getGrade(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(gradeService.getGradeById(id));
    }

    @PostMapping
    public ResponseEntity<GradeResponseDTO> createGrade(@RequestBody GradeRequestDTO grade) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.createGrade(grade));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<GradeResponseDTO> updateGrade(@PathVariable Long id, @RequestBody GradeRequestDTO grade) {
        return ResponseEntity.ok(gradeService.updateGrade(id, grade));
    }
}
