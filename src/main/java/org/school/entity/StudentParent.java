package org.school.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "parent_eleve")
public class StudentParent {

    @EmbeddedId
    private StudentParentId id = new  StudentParentId();

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "eleve_id")
    private Student student;

    @ManyToOne
    @MapsId("parentId")
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @Column(name = "lien_parental")
    private String relationshipType;
}
