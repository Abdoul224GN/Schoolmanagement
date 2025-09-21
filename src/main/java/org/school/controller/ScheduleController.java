package org.school.controller;

import lombok.AllArgsConstructor;
import org.school.service.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
