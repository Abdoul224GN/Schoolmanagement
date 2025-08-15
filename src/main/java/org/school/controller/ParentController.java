package org.school.controller;

import lombok.AllArgsConstructor;
import org.school.dto.ParentRequestDTO;
import org.school.dto.ParentResponseDTO;
import org.school.service.ParentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "api/parent")
@AllArgsConstructor
public class ParentController {

    private ParentService parentService;

    @GetMapping
    public ResponseEntity<List<ParentResponseDTO>> getAllParents() {
        return ResponseEntity.status(HttpStatus.OK).body(parentService.getAllParents());
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
}
