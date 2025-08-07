package org.school.dto;

import lombok.Builder;
import org.school.entity.Grade;

/**
 * DTO for {@link Grade}
 */
@Builder
public record GradeRequestDTO(String name) {
}
