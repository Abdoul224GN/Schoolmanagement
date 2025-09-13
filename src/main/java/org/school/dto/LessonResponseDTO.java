package org.school.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * DTO for {@link org.school.entity.Lesson}
 */
@Builder
public record LessonResponseDTO(
        Long id,
        String name,
        LocalDate day,
        LocalTime startTime,
        LocalTime endTime,
        Long subjectId,
        String subject,
        Long classeId,
        String classe,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
