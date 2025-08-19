package org.school.mapper;

import org.school.dto.ParentResponseDTO;
import org.school.entity.StudentParent;

import java.time.LocalDateTime;

public class StudentParentMapper {

    public static ParentResponseDTO toParentResponseDTO(StudentParent sp) {
        return new ParentResponseDTO(
                sp.getParent().getId(),
                sp.getParent().getFirstName() + " " + sp.getParent().getLastName(), // concat prénom + nom
                sp.getParent().getPhone(),
                sp.getParent().getPhoto(),
                sp.getParent().getCreatedAt() != null ? sp.getParent().getCreatedAt() : LocalDateTime.now(),
                sp.getParent().getUpdatedAt() != null ? sp.getParent().getUpdatedAt() : LocalDateTime.now()
        );
    }
}
