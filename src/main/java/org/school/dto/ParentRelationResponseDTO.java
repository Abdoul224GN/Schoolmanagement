package org.school.dto;

public record ParentRelationResponseDTO(
        ParentResponseDTO parent,
        String typeRelation
) {
}

