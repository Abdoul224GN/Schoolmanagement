package org.school.dto;

import lombok.Builder;
import org.school.entity.Teacher;

import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link Teacher}
 */
@Builder
public record TeacherRequestDTO(
        String firstName,
        String lastName,
        String sex,
        LocalDate birthDate,
        String phone,
        String email,
        String photo,
        Set<Long> subjectIds
) {
}
