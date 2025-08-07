package org.school.dto;

import lombok.Builder;
import org.school.entity.Grade;

import java.time.LocalDateTime;

/**
 * DTO for {@link Grade}
 */
@Builder
public record GradeResponseDTO(
        Long id, String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}