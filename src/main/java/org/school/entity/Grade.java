package org.school.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "niveau")
@Getter
@Setter
public class Grade extends AbstractEntity {

    @Id
    @Column(name = "niveau_id")
    private Long id;

    @Column(name = "nom")
    private String name;
}
