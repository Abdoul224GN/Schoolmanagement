package org.school.dto;

import java.time.LocalDate;
import java.util.Set;

public record StudentRequestDTO(
        String firstName,
        String lastName,
        String sex,
        LocalDate birthDate,
        String address,
        String photo,
        Long classeId,
        Set<Long> parentIds
) {}
