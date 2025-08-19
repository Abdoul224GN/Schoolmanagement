package org.school.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "parent_eleve")
public class ParentEleve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relation avec Student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eleve_id")
    private Student student;

    // Relation avec Parent
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    // Champ supplémentaire
    @Column(name = "lien_parental")
    private String typeRelation;  // exemple: "père", "mère", "tuteur"
}
