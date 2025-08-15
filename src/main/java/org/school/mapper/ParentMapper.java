package org.school.mapper;

import org.school.dto.ParentRequestDTO;
import org.school.dto.ParentResponseDTO;
import org.school.entity.Parent;

public class ParentMapper {

    public static ParentResponseDTO toDTO(Parent parent) {
        return ParentResponseDTO.builder()
                .id(parent.getId())
                .name(parent.getFirstName() + " " + parent.getLastName())
                .phone(parent.getPhone())
                .photo(parent.getPhoto())
                .createdAt(parent.getCreatedAt())
                .updatedAt(parent.getUpdatedAt())
                .build();
    }

    public static Parent toEntity(ParentRequestDTO dto) {
        Parent parent = new Parent();
        parent.setFirstName(dto.firstName());
        parent.setLastName(dto.lastName());
        parent.setPhone(dto.phone());
        parent.setPhoto(dto.photo());
        return parent;
    }
}
