package org.school.dto;

import lombok.Builder;
import org.school.entity.Student;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link org.school.entity.Classe}
 */
@Builder
public record ClasseResponseDTO(
        Long id,
        String name,
        TeacherResponseDTO supervisor,
        GradeResponseDTO grade,
        List<StudentResponseDTO> students,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
