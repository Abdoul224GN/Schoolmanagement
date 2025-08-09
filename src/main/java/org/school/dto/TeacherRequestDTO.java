package org.school.dto;

import java.time.LocalDate;

import lombok.Builder;
import org.school.entity.Teacher;
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
        String photo
) {
}
