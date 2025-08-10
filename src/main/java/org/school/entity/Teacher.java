package org.school.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;

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
}
