package org.school.controller;

import lombok.AllArgsConstructor;
import org.school.dto.PaginationResponseDTO;
import org.school.dto.TeacherRequestDTO;
import org.school.dto.TeacherResponseDTO;
import org.school.service.TeacherService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/teacher")
@AllArgsConstructor
public class TeacherController {

    TeacherService teacherService;

    @GetMapping
    public ResponseEntity<PaginationResponseDTO<TeacherResponseDTO>> getAllTeacher(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "firstName") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getAllTeachers(page, size, sortBy, direction));
    }

    @GetMapping(path = "/search")
    public ResponseEntity<PaginationResponseDTO<TeacherResponseDTO>> searchByName(@RequestParam String keyWord) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.searchByName(keyWord));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<TeacherResponseDTO> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getTeacherById(id));
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDTO> createTeacher(@RequestBody TeacherRequestDTO teacher) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(teacher));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherRequestDTO teacher) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.updateTeacher(id, teacher));
    }

    @PostMapping(path = "{id}/photo")
    public ResponseEntity<TeacherResponseDTO> uploadPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.uploadPhoto(id, file));
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<Resource> getPhoto(@PathVariable Long id) throws Exception {
        Resource resource = teacherService.getPhotoByTeacherId(id);

        if (resource == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
