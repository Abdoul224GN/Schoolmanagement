package org.school.dto;

import org.school.entity.Gender;

import java.time.LocalDate;
import java.util.List;

public record StudentRequestDTO(
        String firstName,
        String lastName,
        Gender sex,
        String address,
        String photo,
        LocalDate birthDate,
        Long classeId,
        List<ParentRelationDTO> parentRelations
) {
}
