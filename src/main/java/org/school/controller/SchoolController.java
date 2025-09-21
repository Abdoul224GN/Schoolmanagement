package org.school.controller;

import org.school.entity.School;
import org.school.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public ResponseEntity<School> getSchool() {
        School school = schoolService.getSchool();
        return ResponseEntity.ok(school);
    }

    @PutMapping
    public ResponseEntity<School> updateSchool(@RequestBody School school) {
        School updatedSchool = schoolService.updateSchool(school);
        return ResponseEntity.ok(updatedSchool);
    }
}