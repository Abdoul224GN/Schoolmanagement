package org.school.dto;

import lombok.Builder;
import org.school.entity.Student;

/**
 * DTO for {@link org.school.entity.Presence}
 */
@Builder
public record PresenceRequestDTO(
        Boolean isPresent,
        String comment,
        Long studentId,
        Long lessonID
) {
}
