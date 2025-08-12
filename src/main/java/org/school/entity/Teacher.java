package org.school.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "enseignant")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enseignant_id", nullable = false)
    private Long id;


    @NotNull
    @Column(name = "prenom")
    private String firstName;

    @NotNull
    @Column(name = "nom")
    private String lastName;

    @NotNull
    @Column(name = "sexe")
    private String sex;

    @NotNull
    @Column(name = "date_naissance")
    private LocalDate birthDate;

    @NotNull
    @Column(name = "telephone")
    private String phone;

    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "photo")
    private String photo;


    @ManyToMany()
    @JoinTable(name = "matiere_enseignant",
            joinColumns = @JoinColumn(name = "enseignant_id"),
            inverseJoinColumns = @JoinColumn(name = "matiere_id"))
    private Set<Subject> subjects = new LinkedHashSet<>();

    public void addSubject(Subject subject) {
        subjects.add(subject);
        subject.getTeachers().add(this);
    }

}
