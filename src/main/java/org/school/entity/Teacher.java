package org.school.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "enseignant")
@Getter
@Setter
public class Teacher extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enseignant_id", nullable = false)
    private Long id;


    @NotNull
    @Column(name = "prenom", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "sexe", nullable = false)
    private String sex;

    @NotNull
    @Column(name = "date_naissance", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Column(name = "telephone", nullable = false)
    private String phone;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "photo")
    private String photo;
}
