package org.school.dto;

import lombok.Builder;

/**
 * DTO for {@link org.school.entity.Presence}
 */
@Builder
public record PresenceResponseDTO(
        Long id,
        Boolean isPresent,
        String comment,
        StudentResponseDTO student,
        LessonResponseDTO lesson
) {
}
