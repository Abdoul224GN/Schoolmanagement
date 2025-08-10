package org.school.dto;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * DTO for {@link org.school.entity.Subject}
 */
@Builder
public record SubjectResponseDTO(
        Long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}
