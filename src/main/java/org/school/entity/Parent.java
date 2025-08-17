package org.school.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "parent")
public class Parent extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id", nullable = false)
    private Long id;

    @Column(name = "prenom")
    private String firstName;

    @Column(name = "nom")
    private String lastName;

    @Column(name = "telephone")
    private Integer phone;

    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "parent", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<StudentParent> students = new HashSet<>();

    public void addStudent(Student student, String relationshipType) {
        StudentParent sp = new StudentParent();
        sp.setStudent(student);
        sp.setParent(this);
        sp.setRelationshipType(relationshipType);
        students.add(sp);
        student.getParents().add(sp);
    }

    public void removeStudent(Student student) {
        students.removeIf(sp -> sp.getStudent().equals(student));
        student.getParents().removeIf(sp -> sp.getParent().equals(this));
    }

}