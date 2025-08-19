package org.school.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
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

    @ManyToMany()
    @JoinTable(name = "parent_eleve",
            joinColumns = @JoinColumn(name = "eleve_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private Set<Parent> parents = new HashSet<>();

    public void addParent(Parent parent){
        parents.add(parent);
        parent.getStudents().add(this);
    }
}