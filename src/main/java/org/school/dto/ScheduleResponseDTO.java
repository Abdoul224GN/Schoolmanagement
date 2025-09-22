package org.school.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record ScheduleResponseDTO(
        Long id,
        Long subjectId,
        String subjectName,
        Long classeId,
        String classeName,
        Long teacherId,
        String teacherName,
        DayOfWeek dayOfWeek,
        LocalTime startTime,
        LocalTime endTime,
        String notes
) {
}
