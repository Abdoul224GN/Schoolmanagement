package org.school.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record StudentResponseDTO(
        Long id,
        String name,
        String lastName,
        String sex,
        String address,
        String photo,
        LocalDate birthDate,
        ClasseResponseDTO classe,
        List<ParentRelationResponseDTO> parentRelations
) {
}
