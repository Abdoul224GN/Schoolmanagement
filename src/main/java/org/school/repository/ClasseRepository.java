package org.school.repository;

import org.school.entity.Classe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
    Page<Classe> findByNameContainingIgnoreCaseOrderByNameAsc(String name, Pageable pageable);
}
