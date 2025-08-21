package org.school.controller;

import lombok.AllArgsConstructor;
import org.school.dto.PaginationResponseDTO;
import org.school.dto.ParentRequestDTO;
import org.school.dto.ParentResponseDTO;
import org.school.service.ParentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController()
@RequestMapping(path = "api/parent")
@AllArgsConstructor
public class ParentController {

    private ParentService parentService;

    @GetMapping
    public ResponseEntity<PaginationResponseDTO<ParentResponseDTO>> getAllParents(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(parentService.getAllParents(page, size));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ParentResponseDTO> getParent(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(parentService.getParentById(id));
    }

    @PostMapping
    public ResponseEntity<ParentResponseDTO> createParent(@RequestBody ParentRequestDTO parentRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parentService.createParent(parentRequestDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable Long id) {
        parentService.deleteParent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ParentResponseDTO> updateParent(@PathVariable Long id, @RequestBody ParentRequestDTO parentRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(parentService.updateParent(id, parentRequestDTO));
    }

    @PostMapping(path = "{id}/photo")
    public ResponseEntity<ParentResponseDTO> uploadPhoto(@PathVariable Long id, @RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(parentService.uploadPhoto(id, file));
    }
}
