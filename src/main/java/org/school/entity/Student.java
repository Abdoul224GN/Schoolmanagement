package org.school.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "eleve")
public class Student extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eleve_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "prenom", nullable = false, length = Integer.MAX_VALUE)
    private String firstName;

    @NotNull
    @Column(name = "nom", nullable = false, length = Integer.MAX_VALUE)
    private String lastName;

    @NotNull
    @Column(name = "sexe", nullable = false, length = Integer.MAX_VALUE)
    private String sex;

    @NotNull
    @Column(name = "date_naissance", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Column(name = "adresse", nullable = false, length = Integer.MAX_VALUE)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classe_id")
    private Classe classe;

    @Size(max = 255)
    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentParent> parents = new HashSet<>();

    public void addParent(Parent parent, String relationshipType) {
        StudentParent sp = new StudentParent();
        sp.setStudent(this);
        sp.setParent(parent);
        sp.setRelationshipType(relationshipType);
        parents.add(sp);
        parent.getStudents().add(sp);
    }

    public void removeParent(Parent parent) {
        parents.removeIf(sp -> sp.getParent().equals(parent));
        parent.getStudents().removeIf(sp -> sp.getStudent().equals(this));
    }
}