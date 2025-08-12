package org.school.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "matiere")
public class Subject extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matiere_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "nom")
    private String name;

    @ManyToMany(mappedBy = "subjects")
    private Set<Teacher> teachers = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "subjects")
    Set<Grade> grades = new LinkedHashSet<>();

    @OneToMany(mappedBy = "subject")
    Set<Lesson> lessons = new LinkedHashSet<>();

}