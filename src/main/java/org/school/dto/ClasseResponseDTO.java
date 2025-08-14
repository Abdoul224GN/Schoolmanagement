package org.school.dto;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * DTO for {@link org.school.entity.Classe}
 */
@Builder
public record ClasseResponseDTO(
        Long id,
        String name,
        TeacherResponseDTO supervisor,
        GradeResponseDTO grade,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
