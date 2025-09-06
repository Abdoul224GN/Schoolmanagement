package org.school.dto;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * DTO for {@link org.school.entity.Parent}
 */
@Builder
public record ParentResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String phone,
        String photo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
