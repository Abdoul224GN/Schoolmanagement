package org.school.controller;

import lombok.AllArgsConstructor;
import org.school.dto.DashboardDTO;
import org.school.entity.Gender;
import org.school.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/dashboard")
@AllArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;


    @GetMapping
    public DashboardDTO getDashboard() {
        return DashboardDTO.builder()
                .totalStudents(dashboardService.countStudent())
                .totalTeachers(dashboardService.countTeacher())
                .totalFemaleStudents(dashboardService.countStudentByGender(Gender.FEMININ))
                .totalMaleStudents(dashboardService.countStudentByGender(Gender.MASCULIN))
                .totalClasses(dashboardService.countClasses())
                .build();
    }
}
