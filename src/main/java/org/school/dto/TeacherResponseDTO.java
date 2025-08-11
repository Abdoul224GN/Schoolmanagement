package org.school.dto;

import lombok.Builder;
import org.school.entity.Teacher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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
        LocalDateTime updatedAt,
        Set<SubjectResponseDTO> subjects
) {
}
