package org.school.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link org.school.entity.Student}
 */
@Builder
public record StudentResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String sex,
        String address,
        String photo,
        LocalDate birthDate,
        Long classeId,
        String classe,
        List<ParentRelationResponseDTO> parentRelations
) {
}
