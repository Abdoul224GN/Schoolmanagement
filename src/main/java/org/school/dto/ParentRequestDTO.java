package org.school.dto;

/**
 * DTO for {@link org.school.entity.Parent}
 */
public record ParentRequestDTO(
        String firstName,
        String lastName,
        String address,
        String occupation,
        String phone
) {
}
