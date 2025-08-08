package org.school.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
