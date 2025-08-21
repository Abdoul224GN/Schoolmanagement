package org.school.controller;

import lombok.AllArgsConstructor;
import org.school.dto.PaginationResponseDTO;
import org.school.dto.TeacherRequestDTO;
import org.school.dto.TeacherResponseDTO;
import org.school.service.TeacherService;
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
    public ResponseEntity<PaginationResponseDTO<TeacherResponseDTO>> getAllTeacher(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getAllTeachers(page, size));
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
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.uploadPhoto(id,file));
    }
}
