package org.school.mapper;

import org.school.dto.GradeRequestDTO;
import org.school.dto.GradeResponseDTO;
import org.school.entity.Grade;

public class GradeMapper {

    public static Grade toEntity(GradeRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Grade grade = new Grade();
        grade.setName(dto.name());
        return grade;
    }

    public static GradeResponseDTO toDTO(Grade entity) {
        return GradeResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .updatedAt(entity.getUpdatedAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
