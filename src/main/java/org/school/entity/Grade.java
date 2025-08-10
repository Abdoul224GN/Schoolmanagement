package org.school.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "niveau")
@Getter
@Setter
public class Grade extends AbstractEntity {

    @Id
    @Column(name = "niveau_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String name;

    @ManyToMany
    @JoinTable(name = "niveau_matiere",
            joinColumns = @JoinColumn(name = "niveau_id"), inverseJoinColumns = @JoinColumn(name = "matiere_id"))
    Set<Subject> subjects = new LinkedHashSet<>();
}
