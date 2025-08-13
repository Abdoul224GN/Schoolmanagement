package org.school.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "classe")
public class Classe extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classe_id")
    private Long id;

    @Column(name = "nom")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enseignant_id")
    private Teacher supervisor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "niveau_id")
    private Grade grade;

}