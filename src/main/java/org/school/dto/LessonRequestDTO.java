package org.school.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * DTO for {@link org.school.entity.Lesson}
 */
@Builder
public record LessonRequestDTO(
        String name,
        LocalDate day,
        LocalTime startTime,
        LocalTime endTime,
        Long subjectId,
        Long classeId
) {
}
