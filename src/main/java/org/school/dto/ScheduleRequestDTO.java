package org.school.dto;

import lombok.Builder;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Builder
public record ScheduleRequestDTO(
        Long id,
        Long subjectId,
        Long classeId,
        Long teacherId,
        DayOfWeek dayOfWeek,
        LocalTime starTime,
        LocalTime endTime,
        String notes
) {
}
