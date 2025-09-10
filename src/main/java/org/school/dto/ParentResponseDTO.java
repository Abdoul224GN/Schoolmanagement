package org.school.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

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
        Set<StudentResponseDTO> students,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
