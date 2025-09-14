package org.school.dto;

import lombok.Builder;

@Builder
public record DashboardDTO(
        long totalStudents,
        long totalTeachers,
        long totalClasses,
        long totalFemaleStudents,
        long totalMaleStudents,
        double attendanceRate) {
}
