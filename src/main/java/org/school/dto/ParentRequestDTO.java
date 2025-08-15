package org.school.dto;

/**
 * DTO for {@link org.school.entity.Parent}
 */
public record ParentRequestDTO (
        String firstName,
        String lastName,
        Integer phone,
        String photo
) {
}
