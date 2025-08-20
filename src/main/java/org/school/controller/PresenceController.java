package org.school.controller;

import lombok.AllArgsConstructor;
import org.school.dto.PresenceRequestDTO;
import org.school.dto.PresenceResponseDTO;
import org.school.service.PresenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/presence")
@AllArgsConstructor
public class PresenceController {

    PresenceService presenceService;

    @GetMapping
    public ResponseEntity<List<PresenceResponseDTO>> getAllPresence(){
        return ResponseEntity.status(HttpStatus.OK).body(presenceService.getAllPresences());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<PresenceResponseDTO> getPresenceById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(presenceService.getPresenceById(id));
    }

    @PostMapping
    public ResponseEntity<PresenceResponseDTO> createPresence(@RequestBody PresenceRequestDTO presenceRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(presenceService.createPresence(presenceRequestDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deletePresence(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<PresenceResponseDTO> updatePresence(@PathVariable Long id, @RequestBody PresenceRequestDTO presenceRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(presenceService.updatePresence(id, presenceRequestDTO));
    }
}
