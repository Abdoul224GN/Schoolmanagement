package org.school.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class StudentParentId implements Serializable {

    @Column(name = "eleve_id")
    private Long studentId;

    @Column(name = "parent_id")
    private Long parentId;
}
