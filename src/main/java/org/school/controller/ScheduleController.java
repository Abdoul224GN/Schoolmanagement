package org.school.controller;

import lombok.AllArgsConstructor;
import org.school.dto.ScheduleRequestDTO;
import org.school.dto.ScheduleResponseDTO;
import org.school.entity.Schedule;
import org.school.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/schedule")
@AllArgsConstructor
public class ScheduleController {

    private ScheduleService scheduleService;

    @GetMapping("/classe/{id}")
    public List<Map<String, Object>> getClasseSchedule(@PathVariable Long id) {
        return scheduleService.getScheduleForClasse(id);
    }

    @GetMapping("/teacher/{id}")
    public List<Map<String, Object>> getTeacherSchedule(@PathVariable Long id) {
        return scheduleService.getScheduleForTeacher(id);
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDTO> createSchedule(@RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.addSchedule(scheduleRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        Schedule updatedSchedule = scheduleService.updateSchedule(id, scheduleRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
