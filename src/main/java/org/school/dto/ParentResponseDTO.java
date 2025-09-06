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
        String address,
        String occupation,
        String phone,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
