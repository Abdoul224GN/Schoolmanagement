package org.school.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record StudentResponseDTO(
        Long id,
        String name,
        String sex,
        LocalDate birthDate,
        String address,
        String photo,
        ClasseResponseDTO classe,
        Set<ParentResponseDTO> parents
) {
}
