package org.school.controller;

import lombok.AllArgsConstructor;
import org.school.dto.PaginationResponseDTO;
import org.school.dto.SubjectRequestDTO;
import org.school.dto.SubjectResponseDTO;
import org.school.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/subject")
@AllArgsConstructor
public class SubjectController {

    SubjectService subjectService;

    @GetMapping
    public ResponseEntity<PaginationResponseDTO<SubjectResponseDTO>> getAllSubjects(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.getAllSubjects(page, size, sortBy, direction));
    }

    @GetMapping(path = "/search")
    public ResponseEntity<PaginationResponseDTO<SubjectResponseDTO>> searchByName(@RequestParam String keyWord) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.searchByName(keyWord));
    }

    @PostMapping(path = "{id}")
    public ResponseEntity<SubjectResponseDTO> getSubjectById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.getSubjectById(id));
    }

    @PostMapping
    public ResponseEntity<SubjectResponseDTO> createSubject(@RequestBody SubjectRequestDTO subjectRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.createSubject(subjectRequestDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<SubjectResponseDTO> updateSubject(@PathVariable Long id, @RequestBody SubjectRequestDTO subjectRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.updateSubject(id, subjectRequestDTO));
    }

}
