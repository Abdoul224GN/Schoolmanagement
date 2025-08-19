package org.school.dto;

import java.time.LocalDate;
import java.util.List;

public record StudentRequestDTO(
        String firstName,
        String lastName,
        String sex,
        String address,
        String photo,
        LocalDate birthDate,
        Long classeId,
        List<ParentRelationDTO> parentRelations
) {
}
