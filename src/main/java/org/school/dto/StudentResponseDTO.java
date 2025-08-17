package org.school.dto;

import java.time.LocalDate;
import java.util.Set;

public record StudentResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String sex,
        LocalDate birthDate,
        String address,
        String photo,
        ClasseResponseDTO classe,
        Set<ParentResponseDTO> parents
) {
}
