package org.school.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.school.entity.Teacher;

/**
 * DTO for {@link Teacher}
 */
@Builder
public record TeacherResponseDTO(
        Long id,
        String name,
        String sex,
        LocalDate birthDate,
        String phone,
        String email,
        String photo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
