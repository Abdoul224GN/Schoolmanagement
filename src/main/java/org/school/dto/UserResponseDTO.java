package org.school.dto;

import lombok.Builder;

@Builder
public record UserResponseDTO(
        Long id,
        String username,
        String firstName,
        String lastName,
        String entity
        ) {
}
