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

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ParentEleve> studentRelations = new HashSet<>();

}