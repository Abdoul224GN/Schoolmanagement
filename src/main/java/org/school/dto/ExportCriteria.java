package org.school.dto;

import java.util.List;

public record ExportCriteria(
        List<String> columns,
        Long classeId,
        String sex,
        Boolean includeParents
) {
}
