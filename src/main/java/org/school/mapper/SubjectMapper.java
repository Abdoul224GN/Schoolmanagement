package org.school.mapper;

import org.school.dto.SubjectRequestDTO;
import org.school.dto.SubjectResponseDTO;
import org.school.entity.Subject;

public class SubjectMapper {

    public static Subject toEntity(SubjectRequestDTO subjectRequestDTO) {
        Subject subject = new Subject();
        subject.setName(subjectRequestDTO.name());
        return subject;
    }

    public static SubjectResponseDTO toDTO(Subject subject) {
        return SubjectResponseDTO.builder()
                .id(subject.getId())
                .name(subject.getName())
                .updatedAt(subject.getUpdatedAt())
                .createdAt(subject.getCreatedAt()).build();
    }
}
