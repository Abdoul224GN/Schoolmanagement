package org.school.controller;

import lombok.AllArgsConstructor;
import org.school.dto.LessonRequestDTO;
import org.school.dto.LessonResponseDTO;
import org.school.dto.PaginationResponseDTO;
import org.school.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping(path = "api/lesson")
@AllArgsConstructor
public class LessonController {

    LessonService lessonService;

    @GetMapping()
    public ResponseEntity<PaginationResponseDTO<LessonResponseDTO>> getAllLessons(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.getAllLessons(page, size));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<LessonResponseDTO> getLessonById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.getLessonById(id));
    }

    @PostMapping
    public ResponseEntity<LessonResponseDTO> createLesson(@RequestBody LessonRequestDTO lessonRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.createLesson(lessonRequestDTO));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<LessonResponseDTO> updateLesson(@PathVariable Long id, @RequestBody LessonRequestDTO lessonRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.updateLesson(id, lessonRequestDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
