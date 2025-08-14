package org.school.dto;

import lombok.Builder;

/**
 * DTO for {@link org.school.entity.Classe}
 */
@Builder
public record ClasseResponseDTO(
        Long id,
        String name,
        TeacherResponseDTO supervisor,
        GradeResponseDTO grade
) {
}
