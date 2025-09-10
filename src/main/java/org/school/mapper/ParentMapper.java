package org.school.mapper;

import org.school.dto.ParentRequestDTO;
import org.school.dto.ParentResponseDTO;
import org.school.dto.StudentResponseDTO;
import org.school.entity.Parent;

import java.util.stream.Collectors;

public class ParentMapper {

    public static ParentResponseDTO toDTO(Parent parent) {
        return ParentResponseDTO.builder()
                .id(parent.getId())
                .firstName(parent.getFirstName())
                .lastName(parent.getLastName())
                .address(parent.getAddress())
                .occupation(parent.getOccupation())
                .phone(parent.getPhone())
                .students(parent.getStudentRelations().stream()
                        .map(rel -> StudentResponseDTO.builder()
                                .id(rel.getStudent().getId())
                                .firstName(rel.getStudent().getFirstName())
                                .lastName(rel.getStudent().getLastName())
                                .build()
                        ).collect(Collectors.toSet())).createdAt(parent.getCreatedAt())
                .updatedAt(parent.getUpdatedAt())
                .build();
    }

    public static Parent toEntity(ParentRequestDTO dto) {
        Parent parent = new Parent();
        parent.setFirstName(dto.firstName());
        parent.setLastName(dto.lastName());
        parent.setAddress(dto.address());
        parent.setOccupation(dto.occupation());
        parent.setPhone(dto.phone());
        return parent;
    }
}
