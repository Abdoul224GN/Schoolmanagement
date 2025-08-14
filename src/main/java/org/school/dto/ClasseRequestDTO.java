package org.school.dto;

import lombok.Builder;

/**
 * DTO for {@link org.school.entity.Classe}
 */
@Builder
public record ClasseRequestDTO(
        String name,
        Long supervisorId,
        Long gradeId
) {
}
