package org.school.dto;

import lombok.Builder;

@Builder
public record UserResponseDTO(
        Long id,
        Long userId,
        String username,
        String firstName,
        String lastName,
        String entity
) {
}
