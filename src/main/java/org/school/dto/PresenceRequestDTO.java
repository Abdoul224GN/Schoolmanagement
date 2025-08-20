package org.school.dto;

import lombok.Builder;

/**
 * DTO for {@link org.school.entity.Presence}
 */
@Builder
public record PresenceRequestDTO(
        Boolean isPresent,
        String comment,
        Long studentId,
        Long lessonId
) {
}
