package org.school.dto;

import lombok.Builder;

@Builder
public record UserResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String entity
        ) {
}
